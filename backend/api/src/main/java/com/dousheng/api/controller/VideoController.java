package com.dousheng.api.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.biz.user.UserContext;
import com.dousheng.api.common.convention.exception.RemoteException;
import com.dousheng.api.common.convention.exception.ServiceException;
import com.dousheng.api.common.convention.result.PagedGridResult;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.dto.req.PublishReqDTO;
import com.dousheng.api.dto.resp.IndexVideoDTO;
import com.dousheng.api.toolkit.PackerUtil;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.dto.req.video.*;
import com.dousheng.dto.resp.video.*;
import com.dousheng.service.VideoRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.api.common.enums.YesOrNoEnum.NO;
import static com.dousheng.api.common.enums.YesOrNoEnum.YES;

@RestController
public class VideoController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private VideoRpcService videoRpcService;

//    @PostMapping("/vlog/publish")
//    public Result<Void> publish(@RequestBody PublishReqDTO requestParam) {
//        com.dousheng.dto.req.video.PublishReqDTO reqDTO = new com.dousheng.dto.req.video.PublishReqDTO();
//        reqDTO.setUserId(UserContext.getUserId());
//        reqDTO.setVideoInfo(PackerUtil.packRpcVideoInfo(requestParam));
//        PublishRespDTO respDTO = videoRpcService.publish(reqDTO);
//        if (respDTO.getCode().equals(SUCCESS_CODE)) {
//            log.info("[publish] success: req={}, resp={}", requestParam, Results.success());
//            return Results.success();
//        }
//        log.error("[publish] error: req={}, resp={}", requestParam, respDTO);
//        return Results.failure(respDTO.getCode(), respDTO.getMessage());
//    }

    /**
     * 由于CDN流量消耗较快，因此测试阶段通过服务端上传至OSS
     */
    @PostMapping("/vlog/publish")
    public Result<Void> publish(@RequestParam("title") String title,
                                @RequestParam("vlogerId") String vlogerId,
                                @RequestParam("width") Integer width,
                                @RequestParam("height") Integer height,
                                @RequestPart("file") MultipartFile file) {
        // 保存视频副本到本地
        String tmpUrl = "D:\\project\\Dousheng\\tmpVideo\\" + UUID.randomUUID() + ".mp4";
        try (FileOutputStream fos = new FileOutputStream(tmpUrl)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }

        com.dousheng.dto.req.video.PublishReqDTO reqDTO = new com.dousheng.dto.req.video.PublishReqDTO();
        VideoInfoDTO videoInfoDTO = VideoInfoDTO.builder().
                title(title).
                authorId(NumberUtil.parseLong(vlogerId)).
                playUrl(tmpUrl).
                width(width).
                height(height).
                build();
        reqDTO.setUserId(UserContext.getUserId());
        reqDTO.setVideoInfo(videoInfoDTO);
        PublishRespDTO respDTO = videoRpcService.publish(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[publish] success: req={}, resp={}", reqDTO, Results.success());
            return Results.success();
        }
        log.error("[publish] error: req={}, resp={}", reqDTO, respDTO);
        return Results.failure(respDTO.getCode(), respDTO.getMessage());
    }

    @DeleteMapping("/vlog/delete")
    public Result<Void> delVideo(@RequestParam("vlogerId") String vlogerId,
                                 @RequestParam("userId") String userId,
                                 @RequestParam("vlogId") String vlogId) {
        DelVideoReqDTO reqDTO = DelVideoReqDTO.builder()
                .userId(NumberUtil.parseLong(userId))
                .authorId(NumberUtil.parseLong(vlogerId))
                .videoId(NumberUtil.parseLong(vlogId))
                .build();
        DelVideoRespDTO respDTO = videoRpcService.delVideo(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[delete] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success();
        }
        log.error("[delete] error: req={}, resp={}", reqDTO, respDTO);
        return Results.failure(respDTO.getCode(), respDTO.getMessage());
    }


    @GetMapping("/vlog/detail")
    public Result<IndexVideoDTO> getVideoDetail(@RequestParam(defaultValue = "") String userId,
                                                @RequestParam String vlogId) {
        GetVideoDetailReqDTO reqDTO = GetVideoDetailReqDTO.builder().
                userId(StrUtil.isEmpty(userId) ? -1L : NumberUtil.parseLong(userId)).
                videoId(NumberUtil.parseLong(vlogId)).
                build();

        GetVideoDetailRespDTO respDTO = videoRpcService.getVideoDetail(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[getVideoDetail] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(PackerUtil.packApiVideoDetail(respDTO.getVideoInfo()));
        }
        Result<IndexVideoDTO> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getVideoDetail] error: req={}, resp={}", reqDTO, result);
        return result;
    }

    @PostMapping("/vlog/changeToPrivate")
    public Result<Void> changeToPrivate(@RequestParam String userId,
                                        @RequestParam String vlogId) {
        ChangeVideoToPrivateReqDTO reqDTO = ChangeVideoToPrivateReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                videoId(NumberUtil.parseLong(vlogId)).
                build();
        ChangeVideoToPrivateRespDTO respDTO = videoRpcService.changeVideoToPrivate(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[changeToPrivate] error: req={}, resp={}", reqDTO, respDTO);
            throw new RemoteException(respDTO.getMessage());
        }
        log.info("[changeToPrivate] success: req={}, resp={}", reqDTO, respDTO);
        return Results.success();
    }

    @PostMapping("/vlog/changeToPublic")
    public Result<Void> changeToPublic(@RequestParam String userId,
                                       @RequestParam String vlogId) {
        ChangeVideoToPublicReqDTO reqDTO = ChangeVideoToPublicReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                videoId(NumberUtil.parseLong(vlogId)).
                build();
        ChangeVideoToPublicRespDTO respDTO = videoRpcService.changeVideoToPublic(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[changeToPublic] error: req={}, resp={}", reqDTO, respDTO);
            throw new RemoteException(respDTO.getMessage());
        }
        log.info("[changeToPublic] success: req={}, resp={}", reqDTO, respDTO);
        return Results.success();
    }

    @GetMapping("/vlog/myPublicList")
    public Result<PagedGridResult> getMyPublicList(@RequestParam String userId,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer pageSize) {
        GetPublicListReqDTO reqDTO = GetPublicListReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                page(page).
                pageSize(pageSize).
                isPrivate(NO.type).
                build();
        GetPublicListRespDTO respDTO = videoRpcService.getPublicList(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packApiPublishList(respDTO.getVideoList())).
                    build();
            log.info("[getMyPublicList] success: req={}, resp={}", reqDTO, Results.success(pagedGridResult));
            return Results.success(pagedGridResult);
        }

        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getMyPublicList] error: req={}, resp={}", reqDTO, result);
        return result;
    }

    @GetMapping("/vlog/myPrivateList")
    public Result<PagedGridResult> getMyPrivateList(@RequestParam String userId,
                                                    @RequestParam Integer page,
                                                    @RequestParam Integer pageSize) {
        GetPrivateListReqDTO reqDTO = GetPrivateListReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                page(page).
                pageSize(pageSize).
                isPrivate(YES.type).
                build();
        GetPrivateListRespDTO respDTO = videoRpcService.getPrivateList(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[getMyPrivateList] error: req={}, resp={}", reqDTO, respDTO);
            throw new RemoteException(respDTO.getMessage());
        }
        PagedGridResult pagedGridResult = PagedGridResult.builder().
                page(respDTO.getPage()).
                total(respDTO.getTotalPage()).
                records(respDTO.getTotalCount()).
                rows(PackerUtil.packApiPublishList(respDTO.getVideoList())).
                build();
        log.info("[getMyPrivateList] success: req={}, resp={}", reqDTO, Results.success(pagedGridResult));
        return Results.success(pagedGridResult);
    }

}
