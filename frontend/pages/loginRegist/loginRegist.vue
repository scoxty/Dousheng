<template>
	<view class="page">
		<view :style="{ height: statusBarHeight + 'px' }"></view>

		<image src="../../static/images/icon-close-black.png"
			style="width: 50rpx;height: 50rpx;"
			@click="close()" />

		<view class="login-box">
			<text class="title-text">{{ isLogin ? '登录发现更多精彩' : '注册您的新账号' }}</text>

			<view class="desc-text">
				请遵守平台用户协议和隐私政策及运营商服务协议，运营商可能对你提供的信息进行验证
			</view>

			<view class="input-wrapper">
				<input class="input" type="text" v-model="username" placeholder="Username" />
			</view>
			<view class="input-wrapper">
				<input class="input" type="password" v-model="password" placeholder="Password" />
			</view>
			
			<view
				class="btn"
				:class="{ 'btn-pressed': loginTouched }"
				@touchstart="touchstartLogin"
				@touchend="touchendLogin"
				@click="loginOrRegister">
				<text class="btn-text">{{ isLogin ? '登录' : '注册' }}</text>
			</view>

			<view class="switch-text" @click="toggleLogin">
				<text>{{ isLogin ? '点击注册新账号' : '点击登录已有账号' }}</text>
			</view>
		</view>
	</view>
</template>

<script>
	var system = uni.getSystemInfoSync();
	var app = getApp();

	export default {
		data() {
			return {
				statusBarHeight: 0,
				username: '',
				password: '',
				loginTouched: false,
				isLogin: true
			};
		},
		onLoad() {
			this.statusBarHeight = system.statusBarHeight;
		},
		methods: {
			touchstartLogin() {
				this.loginTouched = true;
			},
			touchendLogin() {
				this.loginTouched = false;
			},
			toggleLogin() {
				this.isLogin = !this.isLogin;
			},
			close() {
				uni.navigateBack({
					delta: 1,
					animationType: "slide-out-bottom"
				});
			},
			loginOrRegister() {
				if (this.isLogin) {
					this.login();
				} else {
					this.register();
				}
			},
			register() {
				if (!this.username || !this.password) {
					uni.showToast({
						title: "请输入账号和密码",
						icon: "none"
					});
					return;
				}
				
				const serverUrl = app.globalData.serverUrl;
				
				uni.request({
					method: "POST",
					url: serverUrl + "/passport/register",
					data: {
						username: this.username,
						password: this.password
					},
					success: (res) => {
						if (res.data.code == "0") {
							uni.showToast({
								title: "注册成功，正在自动登录",
								icon: "none",
								duration: 1500
							});
							this.login();
						} else {
							uni.showToast({
								title: res.data.message,
								icon: "none"
							});
						}
					}
				});
			},
			login() {
				if (!this.username || !this.password) {
					uni.showToast({
						title: "请输入账号和密码",
						icon: "none"
					});
					return;
				}

				const serverUrl = app.globalData.serverUrl;

				uni.request({
					method: "POST",
					url: serverUrl + "/passport/login",
					data: {
						username: this.username,
						password: this.password
					},
					success: (res) => {
						if (res.data.code == "0") {
							const userInfo = res.data.data;
							app.setUserInfoSession(userInfo);
							app.setUserSessionToken(userInfo.userToken);
							uni.reLaunch({ url: "../me/me" });
							uni.showToast({
								title: "Welcome! " + userInfo.nickname,
								icon: "none",
								duration: 1500
							});
						} else {
							uni.showToast({
								title: res.data.message,
								icon: "none"
							});
						}
					}
				});
			}
		}
	};
</script>

<style>
.page {
	flex: 1;
	background-color: #ffffff;
	padding: 40rpx;
}
.login-box {
	margin-top: 100rpx;
}
.title-text {
	font-size: 48rpx;
	font-weight: bold;
	margin-bottom: 20rpx;
}
.desc-text {
	font-size: 26rpx;
	color: #808080;
	margin-bottom: 50rpx;
	line-height: 40rpx;
	text-align: justify;
	white-space: normal;
	word-wrap: break-word;
}
.input-wrapper {
	background-color: #f5f5f5;
	border: 1px solid #ccc;
	border-radius: 12rpx;
	padding: 0 20rpx;
	height: 100rpx;
	margin-bottom: 30rpx;
	display: flex;
	align-items: center;
}
.input {
	width: 100%;
	font-size: 32rpx;
	color: #000;
}
.btn {
	background-color: #fa4b60;
	border-radius: 20rpx;
	height: 100rpx;
	display: flex;
	justify-content: center;
	align-items: center;
	transition: background-color 0.3s;
}
.btn-pressed {
	background-color: #ec324a;
}
.btn-text {
	color: #fff;
	font-size: 36rpx;
	font-weight: bold;
}
.switch-text {
	text-align: left;
	margin-top: 30rpx;
	font-size: 28rpx;
	color: #999;
}
</style>
