"use weex:vue";
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/*!***************************************************************************!*\
  !*** D:/project/Dousheng/frontend/main.js?{"page":"pages%2Fchat%2Fchat"} ***!
  \***************************************************************************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var uni_app_style__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! uni-app-style */ 1);\n/* harmony import */ var uni_app_style__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(uni_app_style__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony import */ var uni_polyfill__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! uni-polyfill */ 4);\n/* harmony import */ var uni_polyfill__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(uni_polyfill__WEBPACK_IMPORTED_MODULE_1__);\n/* harmony import */ var _pages_chat_chat_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./pages/chat/chat.nvue?mpType=page */ 5);\n\n        \n        \n        \n        \n        _pages_chat_chat_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].mpType = 'page'\n        _pages_chat_chat_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].route = 'pages/chat/chat'\n        _pages_chat_chat_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].el = '#root'\n        new Vue(_pages_chat_chat_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"])\n        //# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7O0FBRUEsUUFBOEI7QUFDOUIsUUFBNkI7QUFDN0IsUUFBNEQ7QUFDNUQsUUFBUSx5RUFBRztBQUNYLFFBQVEseUVBQUc7QUFDWCxRQUFRLHlFQUFHO0FBQ1gsZ0JBQWdCLHlFQUFHIiwiZmlsZSI6IjAuanMiLCJzb3VyY2VzQ29udGVudCI6WyJcbiAgICAgICAgXG4gICAgICAgIGltcG9ydCAndW5pLWFwcC1zdHlsZSdcbiAgICAgICAgaW1wb3J0ICd1bmktcG9seWZpbGwnXG4gICAgICAgIGltcG9ydCBBcHAgZnJvbSAnLi9wYWdlcy9jaGF0L2NoYXQubnZ1ZT9tcFR5cGU9cGFnZSdcbiAgICAgICAgQXBwLm1wVHlwZSA9ICdwYWdlJ1xuICAgICAgICBBcHAucm91dGUgPSAncGFnZXMvY2hhdC9jaGF0J1xuICAgICAgICBBcHAuZWwgPSAnI3Jvb3QnXG4gICAgICAgIG5ldyBWdWUoQXBwKVxuICAgICAgICAiXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///0\n");

/***/ }),
/* 1 */
/*!****************************************************************!*\
  !*** D:/project/Dousheng/frontend/main.js?{"type":"appStyle"} ***!
  \****************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("Vue.prototype.__$appStyle__ = {}\nVue.prototype.__merge_style && Vue.prototype.__merge_style(__webpack_require__(/*! ./App.vue?vue&type=style&index=0&lang=css */ 2).default,Vue.prototype.__$appStyle__)\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0EsMkRBQTJELG1CQUFPLENBQUMsa0RBQTJDIiwiZmlsZSI6IjEuanMiLCJzb3VyY2VzQ29udGVudCI6WyJWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18gPSB7fVxuVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlICYmIFZ1ZS5wcm90b3R5cGUuX19tZXJnZV9zdHlsZShyZXF1aXJlKFwiLi9BcHAudnVlP3Z1ZSZ0eXBlPXN0eWxlJmluZGV4PTAmbGFuZz1jc3NcIikuZGVmYXVsdCxWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18pXG4iXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///1\n");

/***/ }),
/* 2 */
/*!****************************************************************************!*\
  !*** D:/project/Dousheng/frontend/App.vue?vue&type=style&index=0&lang=css ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!../../../HBuilderX/plugins/uniapp-cli/node_modules/postcss-loader/src??ref--10-oneOf-0-2!../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./App.vue?vue&type=style&index=0&lang=css */ 3);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),
/* 3 */
/*!*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!./node_modules/postcss-loader/src??ref--10-oneOf-0-2!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/project/Dousheng/frontend/App.vue?vue&type=style&index=0&lang=css ***!
  \*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = {
  ".activate-line": {
    "": {
      "backgroundColor": [
        "#FFFFFF",
        0,
        0,
        1
      ]
    }
  },
  "@VERSION": 2
}

/***/ }),
/* 4 */
/*!*******************************************************************!*\
  !*** ./node_modules/@dcloudio/uni-cli-shared/lib/uni-polyfill.js ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

if (typeof Promise !== 'undefined' && !Promise.prototype.finally) {
  Promise.prototype.finally = function (callback) {
    var promise = this.constructor;
    return this.then(function (value) {
      return promise.resolve(callback()).then(function () {
        return value;
      });
    }, function (reason) {
      return promise.resolve(callback()).then(function () {
        throw reason;
      });
    });
  };
}
if (typeof uni !== 'undefined' && uni && uni.requireGlobal) {
  var global = uni.requireGlobal();
  ArrayBuffer = global.ArrayBuffer;
  Int8Array = global.Int8Array;
  Uint8Array = global.Uint8Array;
  Uint8ClampedArray = global.Uint8ClampedArray;
  Int16Array = global.Int16Array;
  Uint16Array = global.Uint16Array;
  Int32Array = global.Int32Array;
  Uint32Array = global.Uint32Array;
  Float32Array = global.Float32Array;
  Float64Array = global.Float64Array;
  BigInt64Array = global.BigInt64Array;
  BigUint64Array = global.BigUint64Array;
}

/***/ }),
/* 5 */
/*!*********************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/chat/chat.nvue?mpType=page ***!
  \*********************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./chat.nvue?vue&type=template&id=d5c8bdb0&scoped=true&mpType=page */ 6);\n/* harmony import */ var _chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./chat.nvue?vue&type=script&lang=js&mpType=page */ 8);\n/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__) if([\"default\"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__[key]; }) }(__WEBPACK_IMPORT_KEY__));\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/runtime/componentNormalizer.js */ 18);\n\nvar renderjs\n\n\nfunction injectStyles (context) {\n  \n  if(!this.options.style){\n          this.options.style = {}\n      }\n      if(Vue.prototype.__merge_style && Vue.prototype.__$appStyle__){\n        Vue.prototype.__merge_style(Vue.prototype.__$appStyle__, this.options.style)\n      }\n      if(Vue.prototype.__merge_style){\n                Vue.prototype.__merge_style(__webpack_require__(/*! ./chat.nvue?vue&type=style&index=0&id=d5c8bdb0&scoped=true&lang=css&mpType=page */ 16).default, this.options.style)\n            }else{\n                Object.assign(this.options.style,__webpack_require__(/*! ./chat.nvue?vue&type=style&index=0&id=d5c8bdb0&scoped=true&lang=css&mpType=page */ 16).default)\n            }\n\n}\n\n/* normalize component */\n\nvar component = Object(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__[\"default\"])(\n  _chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__[\"default\"],\n  _chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"render\"],\n  _chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"staticRenderFns\"],\n  false,\n  null,\n  \"d5c8bdb0\",\n  \"45f8a1f1\",\n  false,\n  _chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"components\"],\n  renderjs\n)\n\ninjectStyles.call(component)\ncomponent.options.__file = \"pages/chat/chat.nvue\"\n/* harmony default export */ __webpack_exports__[\"default\"] = (component.exports);//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBeUk7QUFDekk7QUFDb0U7QUFDTDtBQUMvRDs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLDRDQUE0QyxtQkFBTyxDQUFDLHlGQUFpRjtBQUNySSxhQUFhO0FBQ2IsaURBQWlELG1CQUFPLENBQUMseUZBQWlGO0FBQzFJOztBQUVBOztBQUVBO0FBQ3NLO0FBQ3RLLGdCQUFnQiw2S0FBVTtBQUMxQixFQUFFLHNGQUFNO0FBQ1IsRUFBRSx1R0FBTTtBQUNSLEVBQUUsZ0hBQWU7QUFDakI7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEVBQUUsMkdBQVU7QUFDWjtBQUNBOztBQUVBO0FBQ0E7QUFDZSxnRiIsImZpbGUiOiI1LmpzIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgcmVuZGVyLCBzdGF0aWNSZW5kZXJGbnMsIHJlY3ljbGFibGVSZW5kZXIsIGNvbXBvbmVudHMgfSBmcm9tIFwiLi9jaGF0Lm52dWU/dnVlJnR5cGU9dGVtcGxhdGUmaWQ9ZDVjOGJkYjAmc2NvcGVkPXRydWUmbXBUeXBlPXBhZ2VcIlxudmFyIHJlbmRlcmpzXG5pbXBvcnQgc2NyaXB0IGZyb20gXCIuL2NoYXQubnZ1ZT92dWUmdHlwZT1zY3JpcHQmbGFuZz1qcyZtcFR5cGU9cGFnZVwiXG5leHBvcnQgKiBmcm9tIFwiLi9jaGF0Lm52dWU/dnVlJnR5cGU9c2NyaXB0Jmxhbmc9anMmbXBUeXBlPXBhZ2VcIlxuZnVuY3Rpb24gaW5qZWN0U3R5bGVzIChjb250ZXh0KSB7XG4gIFxuICBpZighdGhpcy5vcHRpb25zLnN0eWxlKXtcbiAgICAgICAgICB0aGlzLm9wdGlvbnMuc3R5bGUgPSB7fVxuICAgICAgfVxuICAgICAgaWYoVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlICYmIFZ1ZS5wcm90b3R5cGUuX18kYXBwU3R5bGVfXyl7XG4gICAgICAgIFZ1ZS5wcm90b3R5cGUuX19tZXJnZV9zdHlsZShWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18sIHRoaXMub3B0aW9ucy5zdHlsZSlcbiAgICAgIH1cbiAgICAgIGlmKFZ1ZS5wcm90b3R5cGUuX19tZXJnZV9zdHlsZSl7XG4gICAgICAgICAgICAgICAgVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlKHJlcXVpcmUoXCIuL2NoYXQubnZ1ZT92dWUmdHlwZT1zdHlsZSZpbmRleD0wJmlkPWQ1YzhiZGIwJnNjb3BlZD10cnVlJmxhbmc9Y3NzJm1wVHlwZT1wYWdlXCIpLmRlZmF1bHQsIHRoaXMub3B0aW9ucy5zdHlsZSlcbiAgICAgICAgICAgIH1lbHNle1xuICAgICAgICAgICAgICAgIE9iamVjdC5hc3NpZ24odGhpcy5vcHRpb25zLnN0eWxlLHJlcXVpcmUoXCIuL2NoYXQubnZ1ZT92dWUmdHlwZT1zdHlsZSZpbmRleD0wJmlkPWQ1YzhiZGIwJnNjb3BlZD10cnVlJmxhbmc9Y3NzJm1wVHlwZT1wYWdlXCIpLmRlZmF1bHQpXG4gICAgICAgICAgICB9XG5cbn1cblxuLyogbm9ybWFsaXplIGNvbXBvbmVudCAqL1xuaW1wb3J0IG5vcm1hbGl6ZXIgZnJvbSBcIiEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3Z1ZS1sb2FkZXIvbGliL3J1bnRpbWUvY29tcG9uZW50Tm9ybWFsaXplci5qc1wiXG52YXIgY29tcG9uZW50ID0gbm9ybWFsaXplcihcbiAgc2NyaXB0LFxuICByZW5kZXIsXG4gIHN0YXRpY1JlbmRlckZucyxcbiAgZmFsc2UsXG4gIG51bGwsXG4gIFwiZDVjOGJkYjBcIixcbiAgXCI0NWY4YTFmMVwiLFxuICBmYWxzZSxcbiAgY29tcG9uZW50cyxcbiAgcmVuZGVyanNcbilcblxuaW5qZWN0U3R5bGVzLmNhbGwoY29tcG9uZW50KVxuY29tcG9uZW50Lm9wdGlvbnMuX19maWxlID0gXCJwYWdlcy9jaGF0L2NoYXQubnZ1ZVwiXG5leHBvcnQgZGVmYXVsdCBjb21wb25lbnQuZXhwb3J0cyJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///5\n");

/***/ }),
/* 6 */
/*!***************************************************************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/chat/chat.nvue?vue&type=template&id=d5c8bdb0&scoped=true&mpType=page ***!
  \***************************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/template.js!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--8-0!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./chat.nvue?vue&type=template&id=d5c8bdb0&scoped=true&mpType=page */ 7);
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "render", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__["render"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__["recyclableRender"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "components", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_template_id_d5c8bdb0_scoped_true_mpType_page__WEBPACK_IMPORTED_MODULE_0__["components"]; });



/***/ }),
/* 7 */
/*!**************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/template.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--8-0!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/project/Dousheng/frontend/pages/chat/chat.nvue?vue&type=template&id=d5c8bdb0&scoped=true&mpType=page ***!
  \**************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "render", function() { return render; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return staticRenderFns; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return recyclableRender; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "components", function() { return components; });
var components
var render = function () {
  var _vm = this
  var _h = _vm.$createElement
  var _c = _vm._self._c || _h
  return _c(
    "scroll-view",
    {
      staticStyle: { flexDirection: "column" },
      attrs: {
        scrollY: true,
        showScrollbar: true,
        enableBackToTop: true,
        bubble: "true",
      },
    },
    [
      _c(
        "view",
        { staticClass: ["chat-page"] },
        [
          _c(
            "view",
            { staticClass: ["chat-header"] },
            [
              _c("u-image", {
                staticClass: ["header-face"],
                attrs: { src: _vm.friend.face },
              }),
              _c(
                "u-text",
                {
                  staticClass: ["header-name"],
                  appendAsTree: true,
                  attrs: { append: "tree" },
                },
                [_vm._v(_vm._s(_vm.friend.nickname))]
              ),
            ],
            1
          ),
          _c(
            "scroll-view",
            {
              staticClass: ["chat-body"],
              attrs: {
                scrollY: true,
                scrollWithAnimation: true,
                scrollTop: _vm.scrollTop,
              },
            },
            [
              _vm._l(_vm.list, function (msg, index) {
                return _c(
                  "view",
                  {
                    key: index,
                    staticClass: ["msg-wrapper"],
                    class: msg.senderId === _vm.loginUserId ? "self" : "friend",
                  },
                  [
                    msg.senderId !== _vm.loginUserId
                      ? _c("u-image", {
                          staticClass: ["msg-face"],
                          attrs: { src: _vm.friend.face },
                        })
                      : _vm._e(),
                    _c("view", { staticClass: ["msg-bubble"] }, [
                      _c("u-text", [_vm._v(_vm._s(msg.content))]),
                    ]),
                    msg.senderId === _vm.loginUserId
                      ? _c("u-image", {
                          staticClass: ["msg-face"],
                          attrs: { src: _vm.me.face },
                        })
                      : _vm._e(),
                  ],
                  1
                )
              }),
              _c("view", { staticStyle: { height: "20rpx" } }),
            ],
            2
          ),
          _c(
            "view",
            { staticClass: ["chat-input"] },
            [
              _c("u-input", {
                staticClass: ["input"],
                attrs: { placeholder: "请输入消息…", value: _vm.content },
                on: {
                  input: function ($event) {
                    _vm.content = $event.detail.value
                  },
                },
              }),
              _c(
                "view",
                { staticClass: ["send-btn"], on: { click: _vm.sendMsg } },
                [_c("u-text", [_vm._v("发送")])]
              ),
            ],
            1
          ),
          _c("view", {
            staticStyle: { height: "1px" },
            attrs: { id: "last-msg-item" },
          }),
        ],
        1
      ),
    ]
  )
}
var recyclableRender = false
var staticRenderFns = []
render._withStripped = true



/***/ }),
/* 8 */
/*!*********************************************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/chat/chat.nvue?vue&type=script&lang=js&mpType=page ***!
  \*********************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/babel-loader/lib??ref--5-0!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--5-1!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./chat.nvue?vue&type=script&lang=js&mpType=page */ 9);\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__) if([\"default\"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));\n /* harmony default export */ __webpack_exports__[\"default\"] = (_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0___default.a); //# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQXdiLENBQWdCLG9kQUFHLEVBQUMiLCJmaWxlIjoiOC5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBtb2QgZnJvbSBcIi0hLi4vLi4vLi4vLi4vLi4vSEJ1aWxkZXJYL3BsdWdpbnMvdW5pYXBwLWNsaS9ub2RlX21vZHVsZXMvYmFiZWwtbG9hZGVyL2xpYi9pbmRleC5qcz8/cmVmLS01LTAhLi4vLi4vLi4vLi4vLi4vSEJ1aWxkZXJYL3BsdWdpbnMvdW5pYXBwLWNsaS9ub2RlX21vZHVsZXMvQGRjbG91ZGlvL3Z1ZS1jbGktcGx1Z2luLXVuaS9wYWNrYWdlcy93ZWJwYWNrLXByZXByb2Nlc3MtbG9hZGVyL2luZGV4LmpzPz9yZWYtLTUtMSEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3Z1ZS1sb2FkZXIvbGliL2luZGV4LmpzPz92dWUtbG9hZGVyLW9wdGlvbnMhLi9jaGF0Lm52dWU/dnVlJnR5cGU9c2NyaXB0Jmxhbmc9anMmbXBUeXBlPXBhZ2VcIjsgZXhwb3J0IGRlZmF1bHQgbW9kOyBleHBvcnQgKiBmcm9tIFwiLSEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9iYWJlbC1sb2FkZXIvbGliL2luZGV4LmpzPz9yZWYtLTUtMCEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3dlYnBhY2stcHJlcHJvY2Vzcy1sb2FkZXIvaW5kZXguanM/P3JlZi0tNS0xIS4uLy4uLy4uLy4uLy4uL0hCdWlsZGVyWC9wbHVnaW5zL3VuaWFwcC1jbGkvbm9kZV9tb2R1bGVzL0BkY2xvdWRpby92dWUtY2xpLXBsdWdpbi11bmkvcGFja2FnZXMvdnVlLWxvYWRlci9saWIvaW5kZXguanM/P3Z1ZS1sb2FkZXItb3B0aW9ucyEuL2NoYXQubnZ1ZT92dWUmdHlwZT1zY3JpcHQmbGFuZz1qcyZtcFR5cGU9cGFnZVwiIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///8\n");

/***/ }),
/* 9 */
/*!************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib??ref--5-0!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--5-1!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/project/Dousheng/frontend/pages/chat/chat.nvue?vue&type=script&lang=js&mpType=page ***!
  \************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
eval("/* WEBPACK VAR INJECTION */(function(__f__) {\n\nvar _interopRequireDefault = __webpack_require__(/*! @babel/runtime/helpers/interopRequireDefault */ 11);\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\nexports.default = void 0;\nvar _defineProperty2 = _interopRequireDefault(__webpack_require__(/*! @babel/runtime/helpers/defineProperty */ 12));\nvar _methods;\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n\nvar app = getApp();\nvar _default = {\n  data: function data() {\n    return {\n      content: '',\n      list: [],\n      scrollTop: 0,\n      friend: {\n        nickname: '',\n        face: '',\n        userId: ''\n      },\n      me: {},\n      loginUserId: '',\n      // socket是否开启\n      socketOpen: false,\n      // 定时器\n      timer: null,\n      // 链接\n      surl: '',\n      // WebSocket服务器URL\n\n      // 底部id用于定位到底部\n      scrollIntoView: \"\",\n      // 键盘高度\n      keyboardHeight: 0,\n      // 监听键盘高度的方法\n      listener: null,\n      flag: 0\n    };\n  },\n  onLoad: function onLoad(param) {\n    var _this = this;\n    this.setUserInfo(param);\n    this.getMessageList();\n\n    // 开启键盘高度监听\n    this.listenerKeyboardHeight();\n\n    // socket初始化\n    this.init();\n\n    // 定时器，定时判断socket有没有掉线\n    this.timer = setInterval(function () {\n      _this.isSocketConnct();\n    }, 2000);\n  },\n  beforeDestroy: function beforeDestroy() {\n    // 关闭定时器\n    clearInterval(this.timer);\n\n    // 关闭键盘高度监听\n    uni.offKeyboardHeightChange(this.listener);\n\n    // 关闭Socket\n    this.closeSocket();\n  },\n  methods: (_methods = {\n    setUserInfo: function setUserInfo(param) {\n      var friend = {};\n      if (param.friendInfo) {\n        try {\n          friend = JSON.parse(decodeURIComponent(param.friendInfo));\n        } catch (e) {\n          __f__(\"error\", \"解析 friendInfo 失败：\", e, \" at pages/chat/chat.nvue:100\");\n        }\n      }\n      this.friend = friend;\n      var myUserInfo = getApp().getUserInfoSession();\n      if (myUserInfo == null) {\n        return;\n      }\n      this.me = myUserInfo;\n      this.loginUserId = myUserInfo.id;\n    },\n    getMessageList: function getMessageList() {\n      // mock 数据\n      this.list = [{\n        content: '你好啊',\n        senderId: '123',\n        recevierId: \"1909990512585195521\"\n      }, {\n        content: '请问剪辑软件是什么',\n        senderId: '123',\n        recevierId: \"1909990512585195521\"\n      }, {\n        content: '我用的是剪映～',\n        senderId: '1909990512585195521',\n        recevierId: \"1909990512585195521\"\n      }];\n      this.$nextTick(this.scrollToBottom);\n    },\n    sendMsg: function sendMsg() {\n      if (!this.content) {\n        uni.showToast({\n          title: \"发送的消息不能为空\",\n          icon: \"none\"\n        });\n        return;\n      }\n      this.list.push({\n        content: this.content,\n        senderId: this.loginUserId,\n        recevierId: this.friend.userId\n      });\n      this.content = '';\n      this.$nextTick(this.scrollToBottom);\n    }\n  }, (0, _defineProperty2.default)(_methods, \"sendMsg\", function sendMsg() {\n    var _this2 = this;\n    if (!this.content) {\n      uni.showToast({\n        title: \"发送的消息不能为空\",\n        icon: \"none\"\n      });\n      return;\n    }\n    var data = {\n      senderId: this.loginUserId,\n      recevierId: this.friend.userId,\n      content: this.content\n    };\n    data = JSON.stringify(data);\n    var that = this;\n    if (this.socketOpen) {\n      uni.sendSocketMessage({\n        data: data,\n        success: function success(res) {\n          _this2.content = \"\";\n          setTimeout(function () {\n            var param = JSON.parse(data);\n            that.sendMessageHandle(param);\n          }, 300);\n        },\n        fail: function fail(err) {\n          // 发送失败处理\n        }\n      });\n    } else {\n      // Socket没有开启，重新连接并重新发送消息\n      this.init();\n      setTimeout(function () {\n        _this2.sendMsg();\n      }, 300);\n    }\n  }), (0, _defineProperty2.default)(_methods, \"isSocketConnct\", function isSocketConnct() {\n    if (!this.socketOpen) {\n      __f__(\"log\", \"WebSocket 再次连接！\", \" at pages/chat/chat.nvue:195\");\n      this.init();\n    }\n  }), (0, _defineProperty2.default)(_methods, \"init\", function init() {\n    this.connect();\n    this.openSocket();\n    this.onclose();\n    this.onSocketMessage();\n  }), (0, _defineProperty2.default)(_methods, \"connect\", function connect() {\n    var url = app.globalData.wsServerUrl + \"?userId=\" + this.loginUserId + \"?token=\" + this.me.userToken;\n    __f__(\"log\", url, \" at pages/chat/chat.nvue:211\");\n    uni.connectSocket({\n      url: url,\n      method: 'GET'\n    });\n  }), (0, _defineProperty2.default)(_methods, \"openSocket\", function openSocket() {\n    var that = this;\n    uni.onSocketOpen(function (res) {\n      that.socketOpen = true;\n      __f__(\"log\", 'WebSocket连接已打开！', \" at pages/chat/chat.nvue:222\");\n    });\n  }), (0, _defineProperty2.default)(_methods, \"onclose\", function onclose() {\n    var that = this;\n    uni.onSocketClose(function (res) {\n      that.socketOpen = false;\n      __f__(\"log\", 'WebSocket 已关闭！', \" at pages/chat/chat.nvue:230\");\n    });\n  }), (0, _defineProperty2.default)(_methods, \"closeSocket\", function closeSocket() {\n    uni.closeSocket();\n  }), (0, _defineProperty2.default)(_methods, \"onSocketMessage\", function onSocketMessage() {\n    var _this3 = this;\n    var that = this;\n    uni.onSocketMessage(function (res) {\n      var obj = JSON.parse(res.data);\n      __f__(\"log\", \"接收事件\", obj, \" at pages/chat/chat.nvue:245\");\n      _this3.onMessageHandle(obj);\n    });\n  }), (0, _defineProperty2.default)(_methods, \"onMessageHandle\", function onMessageHandle(obj) {\n    this.list.push({\n      senderId: obj.senderId,\n      recevierId: obj.recevierId,\n      content: obj.content\n    });\n\n    // 滚动到底部\n    this.scrollToBottom();\n  }), (0, _defineProperty2.default)(_methods, \"sendMessageHandle\", function sendMessageHandle(obj) {\n    this.list.push({\n      senderId: obj.senderId,\n      recevierId: obj.recevierId,\n      content: obj.content\n    });\n\n    // 滚动到底部\n    this.scrollToBottom();\n  }), (0, _defineProperty2.default)(_methods, \"scrollToBottom\", function scrollToBottom() {\n    var _this4 = this;\n    this.$nextTick(function () {\n      _this4.scrollIntoView = \"last-msg-item\";\n      _this4.$nextTick(function () {\n        _this4.scrollIntoView = \"\";\n      });\n    });\n  }), (0, _defineProperty2.default)(_methods, \"listenerKeyboardHeight\", function listenerKeyboardHeight() {\n    var _this5 = this;\n    this.listener = function (res) {\n      __f__(\"log\", \"键盘高度\", res.height, \" at pages/chat/chat.nvue:289\");\n      _this5.keyboardHeight = res.height;\n      _this5.$nextTick(function () {\n        _this5.scrollToBottom();\n      });\n    };\n    uni.onKeyboardHeightChange(this.listener);\n  }), _methods)\n};\nexports.default = _default;\n/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/vue-cli-plugin-uni/lib/format-log.js */ 10)[\"default\"]))//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInVuaS1hcHA6Ly8vcGFnZXMvY2hhdC9jaGF0Lm52dWUiXSwibmFtZXMiOlsiZGF0YSIsImNvbnRlbnQiLCJsaXN0Iiwic2Nyb2xsVG9wIiwiZnJpZW5kIiwibmlja25hbWUiLCJmYWNlIiwidXNlcklkIiwibWUiLCJsb2dpblVzZXJJZCIsInNvY2tldE9wZW4iLCJ0aW1lciIsInN1cmwiLCJzY3JvbGxJbnRvVmlldyIsImtleWJvYXJkSGVpZ2h0IiwibGlzdGVuZXIiLCJmbGFnIiwib25Mb2FkIiwiYmVmb3JlRGVzdHJveSIsImNsZWFySW50ZXJ2YWwiLCJ1bmkiLCJtZXRob2RzIiwic2V0VXNlckluZm8iLCJnZXRNZXNzYWdlTGlzdCIsInNlbmRlcklkIiwicmVjZXZpZXJJZCIsInNlbmRNc2ciLCJ0aXRsZSIsImljb24iLCJzdWNjZXNzIiwic2V0VGltZW91dCIsInRoYXQiLCJmYWlsIiwidXJsIiwibWV0aG9kIiwib2JqIl0sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQWdDQTtBQUFBLGVBQ0E7RUFDQUE7SUFDQTtNQUNBQztNQUNBQztNQUNBQztNQUNBQztRQUNBQztRQUNBQztRQUNBQztNQUNBO01BQ0FDO01BQ0FDO01BRUE7TUFDQUM7TUFDQTtNQUNBQztNQUVBO01BQ0FDO01BQUE7O01BRUE7TUFDQUM7TUFFQTtNQUNBQztNQUVBO01BQ0FDO01BRUFDO0lBQ0E7RUFDQTtFQUNBQztJQUFBO0lBQ0E7SUFDQTs7SUFFQTtJQUNBOztJQUVBO0lBQ0E7O0lBRUE7SUFDQTtNQUNBO0lBQ0E7RUFDQTtFQUNBQztJQUNBO0lBQ0FDOztJQUVBO0lBQ0FDOztJQUVBO0lBQ0E7RUFDQTtFQUNBQztJQUNBQztNQUNBO01BQ0E7UUFDQTtVQUNBbEI7UUFDQTtVQUNBO1FBQ0E7TUFDQTtNQUNBO01BQ0E7TUFDQTtRQUNBO01BQ0E7TUFDQTtNQUNBO0lBRUE7SUFDQW1CO01BQ0E7TUFDQTtRQUNBdEI7UUFDQXVCO1FBQ0FDO01BQ0EsR0FDQTtRQUNBeEI7UUFDQXVCO1FBQ0FDO01BQ0EsR0FDQTtRQUNBeEI7UUFDQXVCO1FBQ0FDO01BQ0EsRUFDQTtNQUNBO0lBQ0E7SUFDQUM7TUFDQTtRQUNBTjtVQUNBTztVQUNBQztRQUNBO1FBQ0E7TUFDQTtNQUNBO1FBQ0EzQjtRQUNBdUI7UUFDQUM7TUFDQTtNQUNBO01BQ0E7SUFDQTtFQUFBLHlFQUtBO0lBQUE7SUFDQTtNQUNBTDtRQUNBTztRQUNBQztNQUNBO01BQ0E7SUFDQTtJQUVBO01BQ0FKO01BQ0FDO01BQ0F4QjtJQUNBO0lBQ0FEO0lBRUE7SUFDQTtNQUNBb0I7UUFDQXBCO1FBQ0E2QjtVQUNBO1VBQ0FDO1lBQ0E7WUFDQUM7VUFDQTtRQUNBO1FBQ0FDO1VBQ0E7UUFBQTtNQUVBO0lBQ0E7TUFDQTtNQUNBO01BQ0FGO1FBQ0E7TUFDQTtJQUNBO0VBQ0Esd0ZBR0E7SUFDQTtNQUNBO01BQ0E7SUFDQTtFQUNBLG9FQUdBO0lBQ0E7SUFDQTtJQUNBO0lBQ0E7RUFDQSwwRUFHQTtJQUNBO0lBQ0E7SUFDQVY7TUFDQWE7TUFDQUM7SUFDQTtFQUNBLGdGQUVBO0lBQ0E7SUFDQWQ7TUFDQVc7TUFDQTtJQUNBO0VBQ0EsMEVBRUE7SUFDQTtJQUNBWDtNQUNBVztNQUNBO0lBQ0E7RUFDQSxrRkFHQTtJQUNBWDtFQUNBLDBGQUlBO0lBQUE7SUFDQTtJQUNBQTtNQUNBO01BQ0E7TUFDQTtJQUNBO0VBQ0Esd0ZBR0FlO0lBQ0E7TUFDQVg7TUFDQUM7TUFDQXhCO0lBQ0E7O0lBRUE7SUFDQTtFQUNBLDRGQUdBa0M7SUFFQTtNQUNBWDtNQUNBQztNQUNBeEI7SUFDQTs7SUFFQTtJQUNBO0VBQ0Esd0ZBSUE7SUFBQTtJQUNBO01BQ0E7TUFDQTtRQUNBO01BQ0E7SUFDQTtFQUNBLHdHQUdBO0lBQUE7SUFDQTtNQUNBO01BQ0E7TUFDQTtRQUNBO01BQ0E7SUFDQTtJQUNBbUI7RUFDQTtBQUVBO0FBQUEsMkIiLCJmaWxlIjoiOS5qcyIsInNvdXJjZXNDb250ZW50IjpbIjx0ZW1wbGF0ZT5cclxuXHQ8dmlldyBjbGFzcz1cImNoYXQtcGFnZVwiPlxyXG5cdFx0PCEtLSDpobbpg6jnlKjmiLfkv6Hmga/moI8gLS0+XHJcblx0XHQ8dmlldyBjbGFzcz1cImNoYXQtaGVhZGVyXCI+XHJcblx0XHRcdDxpbWFnZSBjbGFzcz1cImhlYWRlci1mYWNlXCIgOnNyYz1cImZyaWVuZC5mYWNlXCIgLz5cclxuXHRcdFx0PHRleHQgY2xhc3M9XCJoZWFkZXItbmFtZVwiPnt7IGZyaWVuZC5uaWNrbmFtZSB9fTwvdGV4dD5cclxuXHRcdDwvdmlldz5cclxuXHJcblx0XHQ8IS0tIOa2iOaBr+WIl+ihqCAtLT5cclxuXHRcdDxzY3JvbGwtdmlldyBjbGFzcz1cImNoYXQtYm9keVwiIHNjcm9sbC15IHNjcm9sbC13aXRoLWFuaW1hdGlvbiA6c2Nyb2xsLXRvcD1cInNjcm9sbFRvcFwiPlxyXG5cdFx0XHQ8dmlldyBjbGFzcz1cIm1zZy13cmFwcGVyXCIgdi1mb3I9XCIobXNnLCBpbmRleCkgaW4gbGlzdFwiIDprZXk9XCJpbmRleFwiXHJcblx0XHRcdFx0OmNsYXNzPVwibXNnLnNlbmRlcklkID09PSBsb2dpblVzZXJJZCA/ICdzZWxmJyA6ICdmcmllbmQnXCI+XHJcblx0XHRcdFx0PGltYWdlIHYtaWY9XCJtc2cuc2VuZGVySWQgIT09IGxvZ2luVXNlcklkXCIgY2xhc3M9XCJtc2ctZmFjZVwiIDpzcmM9XCJmcmllbmQuZmFjZVwiIC8+XHJcblx0XHRcdFx0PHZpZXcgY2xhc3M9XCJtc2ctYnViYmxlXCI+e3sgbXNnLmNvbnRlbnQgfX08L3ZpZXc+XHJcblx0XHRcdFx0PGltYWdlIHYtaWY9XCJtc2cuc2VuZGVySWQgPT09IGxvZ2luVXNlcklkXCIgY2xhc3M9XCJtc2ctZmFjZVwiIDpzcmM9XCJtZS5mYWNlXCIgLz5cclxuXHRcdFx0PC92aWV3PlxyXG5cdFx0XHQ8dmlldyBzdHlsZT1cImhlaWdodDogMjBycHg7XCI+PC92aWV3PlxyXG5cdFx0PC9zY3JvbGwtdmlldz5cclxuXHJcblx0XHQ8IS0tIOi+k+WFpeahhiAtLT5cclxuXHRcdDx2aWV3IGNsYXNzPVwiY2hhdC1pbnB1dFwiPlxyXG5cdFx0XHQ8aW5wdXQgdi1tb2RlbD1cImNvbnRlbnRcIiBjbGFzcz1cImlucHV0XCIgcGxhY2Vob2xkZXI9XCLor7fovpPlhaXmtojmga/igKZcIiAvPlxyXG5cdFx0XHQ8dmlldyBjbGFzcz1cInNlbmQtYnRuXCIgQGNsaWNrPVwic2VuZE1zZ1wiPuWPkemAgTwvdmlldz5cclxuXHRcdDwvdmlldz5cclxuXHJcblx0XHQ8dmlldyBpZD1cImxhc3QtbXNnLWl0ZW1cIiBzdHlsZT1cImhlaWdodDogMXB4O1wiPjwvdmlldz5cclxuXHQ8L3ZpZXc+XHJcbjwvdGVtcGxhdGU+XHJcblxyXG5cclxuXHJcbjxzY3JpcHQ+XHJcblx0dmFyIGFwcCA9IGdldEFwcCgpO1xyXG5cdGV4cG9ydCBkZWZhdWx0IHtcclxuXHRcdGRhdGEoKSB7XHJcblx0XHRcdHJldHVybiB7XHJcblx0XHRcdFx0Y29udGVudDogJycsXHJcblx0XHRcdFx0bGlzdDogW10sXHJcblx0XHRcdFx0c2Nyb2xsVG9wOiAwLFxyXG5cdFx0XHRcdGZyaWVuZDoge1xyXG5cdFx0XHRcdFx0bmlja25hbWU6ICcnLFxyXG5cdFx0XHRcdFx0ZmFjZTogJycsXHJcblx0XHRcdFx0XHR1c2VySWQ6ICcnXHJcblx0XHRcdFx0fSxcclxuXHRcdFx0XHRtZToge30sXHJcblx0XHRcdFx0bG9naW5Vc2VySWQ6ICcnLFxyXG5cclxuXHRcdFx0XHQvLyBzb2NrZXTmmK/lkKblvIDlkK9cclxuXHRcdFx0XHRzb2NrZXRPcGVuOiBmYWxzZSxcclxuXHRcdFx0XHQvLyDlrprml7blmahcclxuXHRcdFx0XHR0aW1lcjogbnVsbCxcclxuXHJcblx0XHRcdFx0Ly8g6ZO+5o6lXHJcblx0XHRcdFx0c3VybDogJycsIC8vIFdlYlNvY2tldOacjeWKoeWZqFVSTFxyXG5cclxuXHRcdFx0XHQvLyDlupXpg6hpZOeUqOS6juWumuS9jeWIsOW6lemDqFxyXG5cdFx0XHRcdHNjcm9sbEludG9WaWV3OiBcIlwiLFxyXG5cclxuXHRcdFx0XHQvLyDplK7nm5jpq5jluqZcclxuXHRcdFx0XHRrZXlib2FyZEhlaWdodDogMCxcclxuXHJcblx0XHRcdFx0Ly8g55uR5ZCs6ZSu55uY6auY5bqm55qE5pa55rOVXHJcblx0XHRcdFx0bGlzdGVuZXI6IG51bGwsXHJcblxyXG5cdFx0XHRcdGZsYWc6IDBcclxuXHRcdFx0fTtcclxuXHRcdH0sXHJcblx0XHRvbkxvYWQocGFyYW0pIHtcclxuXHRcdFx0dGhpcy5zZXRVc2VySW5mbyhwYXJhbSk7XHJcblx0XHRcdHRoaXMuZ2V0TWVzc2FnZUxpc3QoKTtcclxuXHJcblx0XHRcdC8vIOW8gOWQr+mUruebmOmrmOW6puebkeWQrFxyXG5cdFx0XHR0aGlzLmxpc3RlbmVyS2V5Ym9hcmRIZWlnaHQoKVxyXG5cclxuXHRcdFx0Ly8gc29ja2V05Yid5aeL5YyWXHJcblx0XHRcdHRoaXMuaW5pdCgpXHJcblxyXG5cdFx0XHQvLyDlrprml7blmajvvIzlrprml7bliKTmlq1zb2NrZXTmnInmsqHmnInmjonnur9cclxuXHRcdFx0dGhpcy50aW1lciA9IHNldEludGVydmFsKCgpID0+IHtcclxuXHRcdFx0XHR0aGlzLmlzU29ja2V0Q29ubmN0KClcclxuXHRcdFx0fSwgMjAwMClcclxuXHRcdH0sXHJcblx0XHRiZWZvcmVEZXN0cm95KCkge1xyXG5cdFx0XHQvLyDlhbPpl63lrprml7blmahcclxuXHRcdFx0Y2xlYXJJbnRlcnZhbCh0aGlzLnRpbWVyKVxyXG5cclxuXHRcdFx0Ly8g5YWz6Zet6ZSu55uY6auY5bqm55uR5ZCsXHJcblx0XHRcdHVuaS5vZmZLZXlib2FyZEhlaWdodENoYW5nZSh0aGlzLmxpc3RlbmVyKVxyXG5cclxuXHRcdFx0Ly8g5YWz6ZetU29ja2V0XHJcblx0XHRcdHRoaXMuY2xvc2VTb2NrZXQoKVxyXG5cdFx0fSxcclxuXHRcdG1ldGhvZHM6IHtcclxuXHRcdFx0c2V0VXNlckluZm8ocGFyYW0pIHtcclxuXHRcdFx0XHRsZXQgZnJpZW5kID0ge307XHJcblx0XHRcdFx0aWYgKHBhcmFtLmZyaWVuZEluZm8pIHtcclxuXHRcdFx0XHRcdHRyeSB7XHJcblx0XHRcdFx0XHRcdGZyaWVuZCA9IEpTT04ucGFyc2UoZGVjb2RlVVJJQ29tcG9uZW50KHBhcmFtLmZyaWVuZEluZm8pKTtcclxuXHRcdFx0XHRcdH0gY2F0Y2ggKGUpIHtcclxuXHRcdFx0XHRcdFx0Y29uc29sZS5lcnJvcihcIuino+aekCBmcmllbmRJbmZvIOWksei0pe+8mlwiLCBlKTtcclxuXHRcdFx0XHRcdH1cclxuXHRcdFx0XHR9XHJcblx0XHRcdFx0dGhpcy5mcmllbmQgPSBmcmllbmQ7XHJcblx0XHRcdFx0dmFyIG15VXNlckluZm8gPSBnZXRBcHAoKS5nZXRVc2VySW5mb1Nlc3Npb24oKTtcclxuXHRcdFx0XHRpZiAobXlVc2VySW5mbyA9PSBudWxsKSB7XHJcblx0XHRcdFx0XHRyZXR1cm47XHJcblx0XHRcdFx0fVxyXG5cdFx0XHRcdHRoaXMubWUgPSBteVVzZXJJbmZvO1xyXG5cdFx0XHRcdHRoaXMubG9naW5Vc2VySWQgPSBteVVzZXJJbmZvLmlkO1xyXG5cclxuXHRcdFx0fSxcclxuXHRcdFx0Z2V0TWVzc2FnZUxpc3QoKSB7XHJcblx0XHRcdFx0Ly8gbW9jayDmlbDmja5cclxuXHRcdFx0XHR0aGlzLmxpc3QgPSBbe1xyXG5cdFx0XHRcdFx0XHRjb250ZW50OiAn5L2g5aW95ZWKJyxcclxuXHRcdFx0XHRcdFx0c2VuZGVySWQ6ICcxMjMnLFxyXG5cdFx0XHRcdFx0XHRyZWNldmllcklkOiBcIjE5MDk5OTA1MTI1ODUxOTU1MjFcIlxyXG5cdFx0XHRcdFx0fSxcclxuXHRcdFx0XHRcdHtcclxuXHRcdFx0XHRcdFx0Y29udGVudDogJ+ivt+mXruWJqui+kei9r+S7tuaYr+S7gOS5iCcsXHJcblx0XHRcdFx0XHRcdHNlbmRlcklkOiAnMTIzJyxcclxuXHRcdFx0XHRcdFx0cmVjZXZpZXJJZDogXCIxOTA5OTkwNTEyNTg1MTk1NTIxXCJcclxuXHRcdFx0XHRcdH0sXHJcblx0XHRcdFx0XHR7XHJcblx0XHRcdFx0XHRcdGNvbnRlbnQ6ICfmiJHnlKjnmoTmmK/liarmmKDvvZ4nLFxyXG5cdFx0XHRcdFx0XHRzZW5kZXJJZDogJzE5MDk5OTA1MTI1ODUxOTU1MjEnLFxyXG5cdFx0XHRcdFx0XHRyZWNldmllcklkOiBcIjE5MDk5OTA1MTI1ODUxOTU1MjFcIlxyXG5cdFx0XHRcdFx0fVxyXG5cdFx0XHRcdF07XHJcblx0XHRcdFx0dGhpcy4kbmV4dFRpY2sodGhpcy5zY3JvbGxUb0JvdHRvbSk7XHJcblx0XHRcdH0sXHJcblx0XHRcdHNlbmRNc2coKSB7XHJcblx0XHRcdFx0aWYgKCF0aGlzLmNvbnRlbnQpIHtcclxuXHRcdFx0XHRcdHVuaS5zaG93VG9hc3Qoe1xyXG5cdFx0XHRcdFx0XHR0aXRsZTogXCLlj5HpgIHnmoTmtojmga/kuI3og73kuLrnqbpcIixcclxuXHRcdFx0XHRcdFx0aWNvbjogXCJub25lXCJcclxuXHRcdFx0XHRcdH0pO1xyXG5cdFx0XHRcdFx0cmV0dXJuXHJcblx0XHRcdFx0fVxyXG5cdFx0XHRcdHRoaXMubGlzdC5wdXNoKHtcclxuXHRcdFx0XHRcdGNvbnRlbnQ6IHRoaXMuY29udGVudCxcclxuXHRcdFx0XHRcdHNlbmRlcklkOiB0aGlzLmxvZ2luVXNlcklkLFxyXG5cdFx0XHRcdFx0cmVjZXZpZXJJZDogdGhpcy5mcmllbmQudXNlcklkXHJcblx0XHRcdFx0fSk7XHJcblx0XHRcdFx0dGhpcy5jb250ZW50ID0gJyc7XHJcblx0XHRcdFx0dGhpcy4kbmV4dFRpY2sodGhpcy5zY3JvbGxUb0JvdHRvbSk7XHJcblx0XHRcdH0sXHJcblx0XHRcdC8vIHNjcm9sbFRvQm90dG9tKCkge1xyXG5cdFx0XHQvLyBcdHRoaXMuc2Nyb2xsVG9wID0gOTk5OTk5O1xyXG5cdFx0XHQvLyB9LFxyXG5cdFx0XHQvLyDlj5HpgIHmtojmga9cclxuXHRcdFx0c2VuZE1zZygpIHtcclxuXHRcdFx0XHRpZiAoIXRoaXMuY29udGVudCkge1xyXG5cdFx0XHRcdFx0dW5pLnNob3dUb2FzdCh7XHJcblx0XHRcdFx0XHRcdHRpdGxlOiBcIuWPkemAgeeahOa2iOaBr+S4jeiDveS4uuepulwiLFxyXG5cdFx0XHRcdFx0XHRpY29uOiBcIm5vbmVcIlxyXG5cdFx0XHRcdFx0fSk7XHJcblx0XHRcdFx0XHRyZXR1cm5cclxuXHRcdFx0XHR9XHJcblxyXG5cdFx0XHRcdGxldCBkYXRhID0ge1xyXG5cdFx0XHRcdFx0c2VuZGVySWQ6IHRoaXMubG9naW5Vc2VySWQsXHJcblx0XHRcdFx0XHRyZWNldmllcklkOiB0aGlzLmZyaWVuZC51c2VySWQsXHJcblx0XHRcdFx0XHRjb250ZW50OiB0aGlzLmNvbnRlbnRcclxuXHRcdFx0XHR9XHJcblx0XHRcdFx0ZGF0YSA9IEpTT04uc3RyaW5naWZ5KGRhdGEpO1xyXG5cdFx0XHRcdFxyXG5cdFx0XHRcdGxldCB0aGF0ID0gdGhpc1xyXG5cdFx0XHRcdGlmICh0aGlzLnNvY2tldE9wZW4pIHtcclxuXHRcdFx0XHRcdHVuaS5zZW5kU29ja2V0TWVzc2FnZSh7XHJcblx0XHRcdFx0XHRcdGRhdGEsXHJcblx0XHRcdFx0XHRcdHN1Y2Nlc3M6IChyZXMpID0+IHtcclxuXHRcdFx0XHRcdFx0XHR0aGlzLmNvbnRlbnQgPSBcIlwiO1xyXG5cdFx0XHRcdFx0XHRcdHNldFRpbWVvdXQoKCkgPT4ge1xyXG5cdFx0XHRcdFx0XHRcdFx0bGV0IHBhcmFtID0gSlNPTi5wYXJzZShkYXRhKVxyXG5cdFx0XHRcdFx0XHRcdFx0dGhhdC5zZW5kTWVzc2FnZUhhbmRsZShwYXJhbSlcclxuXHRcdFx0XHRcdFx0XHR9LCAzMDApXHJcblx0XHRcdFx0XHRcdH0sXHJcblx0XHRcdFx0XHRcdGZhaWwoZXJyKSB7XHJcblx0XHRcdFx0XHRcdFx0Ly8g5Y+R6YCB5aSx6LSl5aSE55CGXHJcblx0XHRcdFx0XHRcdH1cclxuXHRcdFx0XHRcdH0pO1xyXG5cdFx0XHRcdH0gZWxzZSB7XHJcblx0XHRcdFx0XHQvLyBTb2NrZXTmsqHmnInlvIDlkK/vvIzph43mlrDov57mjqXlubbph43mlrDlj5HpgIHmtojmga9cclxuXHRcdFx0XHRcdHRoaXMuaW5pdCgpXHJcblx0XHRcdFx0XHRzZXRUaW1lb3V0KCgpID0+IHtcclxuXHRcdFx0XHRcdFx0dGhpcy5zZW5kTXNnKCk7XHJcblx0XHRcdFx0XHR9LCAzMDApXHJcblx0XHRcdFx0fVxyXG5cdFx0XHR9LFxyXG5cclxuXHRcdFx0Ly8g5Yik5pat5piv5ZCm6L+e5o6lXHJcblx0XHRcdGlzU29ja2V0Q29ubmN0KCkge1xyXG5cdFx0XHRcdGlmICghdGhpcy5zb2NrZXRPcGVuKSB7XHJcblx0XHRcdFx0XHRjb25zb2xlLmxvZyhcIldlYlNvY2tldCDlho3mrKHov57mjqXvvIFcIik7XHJcblx0XHRcdFx0XHR0aGlzLmluaXQoKVxyXG5cdFx0XHRcdH1cclxuXHRcdFx0fSxcclxuXHJcblx0XHRcdC8vIOWIneWni+WMllxyXG5cdFx0XHRpbml0KCkge1xyXG5cdFx0XHRcdHRoaXMuY29ubmVjdCgpXHJcblx0XHRcdFx0dGhpcy5vcGVuU29ja2V0KClcclxuXHRcdFx0XHR0aGlzLm9uY2xvc2UoKVxyXG5cdFx0XHRcdHRoaXMub25Tb2NrZXRNZXNzYWdlKClcclxuXHRcdFx0fSxcclxuXHJcblx0XHRcdC8vIOW7uueri+i/nuaOpVxyXG5cdFx0XHRjb25uZWN0KCkge1xyXG5cdFx0XHRcdGNvbnN0IHVybCA9IGFwcC5nbG9iYWxEYXRhLndzU2VydmVyVXJsICsgXCI/dXNlcklkPVwiICsgdGhpcy5sb2dpblVzZXJJZCArIFwiP3Rva2VuPVwiICsgdGhpcy5tZS51c2VyVG9rZW47XHJcblx0XHRcdFx0Y29uc29sZS5sb2codXJsKTtcclxuXHRcdFx0XHR1bmkuY29ubmVjdFNvY2tldCh7XHJcblx0XHRcdFx0XHR1cmw6IHVybCxcclxuXHRcdFx0XHRcdG1ldGhvZDogJ0dFVCdcclxuXHRcdFx0XHR9KTtcclxuXHRcdFx0fSxcclxuXHRcdFx0Ly8g5omT5byAU29jZWtldFxyXG5cdFx0XHRvcGVuU29ja2V0KCkge1xyXG5cdFx0XHRcdGxldCB0aGF0ID0gdGhpc1xyXG5cdFx0XHRcdHVuaS5vblNvY2tldE9wZW4oKHJlcykgPT4ge1xyXG5cdFx0XHRcdFx0dGhhdC5zb2NrZXRPcGVuID0gdHJ1ZVxyXG5cdFx0XHRcdFx0Y29uc29sZS5sb2coJ1dlYlNvY2tldOi/nuaOpeW3suaJk+W8gO+8gScpO1xyXG5cdFx0XHRcdH0pO1xyXG5cdFx0XHR9LFxyXG5cdFx0XHQvLyDnm5HlkKzlhbPpl61cclxuXHRcdFx0b25jbG9zZSgpIHtcclxuXHRcdFx0XHRsZXQgdGhhdCA9IHRoaXNcclxuXHRcdFx0XHR1bmkub25Tb2NrZXRDbG9zZSgocmVzKSA9PiB7XHJcblx0XHRcdFx0XHR0aGF0LnNvY2tldE9wZW4gPSBmYWxzZVxyXG5cdFx0XHRcdFx0Y29uc29sZS5sb2coJ1dlYlNvY2tldCDlt7LlhbPpl63vvIEnKTtcclxuXHRcdFx0XHR9KTtcclxuXHRcdFx0fSxcclxuXHJcblx0XHRcdC8vIOWFs+mXrVxyXG5cdFx0XHRjbG9zZVNvY2tldCgpIHtcclxuXHRcdFx0XHR1bmkuY2xvc2VTb2NrZXQoKTtcclxuXHRcdFx0fSxcclxuXHJcblxyXG5cdFx0XHQvLyDmjqXmlLbkuovku7ZcclxuXHRcdFx0b25Tb2NrZXRNZXNzYWdlKCkge1xyXG5cdFx0XHRcdGxldCB0aGF0ID0gdGhpc1xyXG5cdFx0XHRcdHVuaS5vblNvY2tldE1lc3NhZ2UoKHJlcykgPT4ge1xyXG5cdFx0XHRcdFx0bGV0IG9iaiA9IEpTT04ucGFyc2UocmVzLmRhdGEpXHJcblx0XHRcdFx0XHRjb25zb2xlLmxvZyhcIuaOpeaUtuS6i+S7tlwiLCBvYmopO1xyXG5cdFx0XHRcdFx0dGhpcy5vbk1lc3NhZ2VIYW5kbGUob2JqKVxyXG5cdFx0XHRcdH0pO1xyXG5cdFx0XHR9LFxyXG5cclxuXHRcdFx0Ly8g5o6l5pS2LS0t5Yiw5LqL5Lu25ZCO5aSE55CG55qE5pa55rOVXHJcblx0XHRcdG9uTWVzc2FnZUhhbmRsZShvYmopIHtcclxuXHRcdFx0XHR0aGlzLmxpc3QucHVzaCh7XHJcblx0XHRcdFx0XHRzZW5kZXJJZDogb2JqLnNlbmRlcklkLFxyXG5cdFx0XHRcdFx0cmVjZXZpZXJJZDogb2JqLnJlY2V2aWVySWQsXHJcblx0XHRcdFx0XHRjb250ZW50OiBvYmouY29udGVudFxyXG5cdFx0XHRcdH0pXHJcblxyXG5cdFx0XHRcdC8vIOa7muWKqOWIsOW6lemDqFxyXG5cdFx0XHRcdHRoaXMuc2Nyb2xsVG9Cb3R0b20oKVxyXG5cdFx0XHR9LFxyXG5cclxuXHRcdFx0Ly8g5Y+R6YCBLS0t5raI5oGv5ZCO5aSE55CG55qE5pa55rOVXHJcblx0XHRcdHNlbmRNZXNzYWdlSGFuZGxlKG9iaikge1xyXG5cclxuXHRcdFx0XHR0aGlzLmxpc3QucHVzaCh7XHJcblx0XHRcdFx0XHRzZW5kZXJJZDogb2JqLnNlbmRlcklkLFxyXG5cdFx0XHRcdFx0cmVjZXZpZXJJZDogb2JqLnJlY2V2aWVySWQsXHJcblx0XHRcdFx0XHRjb250ZW50OiBvYmouY29udGVudFxyXG5cdFx0XHRcdH0pO1xyXG5cclxuXHRcdFx0XHQvLyDmu5rliqjliLDlupXpg6hcclxuXHRcdFx0XHR0aGlzLnNjcm9sbFRvQm90dG9tKClcclxuXHRcdFx0fSxcclxuXHJcblxyXG5cdFx0XHQvLyDlrprkvY3liLDlupXpg6hcclxuXHRcdFx0c2Nyb2xsVG9Cb3R0b20oKSB7XHJcblx0XHRcdFx0dGhpcy4kbmV4dFRpY2soKCkgPT4ge1xyXG5cdFx0XHRcdFx0dGhpcy5zY3JvbGxJbnRvVmlldyA9IFwibGFzdC1tc2ctaXRlbVwiXHJcblx0XHRcdFx0XHR0aGlzLiRuZXh0VGljaygoKSA9PiB7XHJcblx0XHRcdFx0XHRcdHRoaXMuc2Nyb2xsSW50b1ZpZXcgPSBcIlwiXHJcblx0XHRcdFx0XHR9KVxyXG5cdFx0XHRcdH0pXHJcblx0XHRcdH0sXHJcblxyXG5cdFx0XHQvLyDlvIDlkK/plK7nm5jpq5jluqbnmoTnm5HlkKxcclxuXHRcdFx0bGlzdGVuZXJLZXlib2FyZEhlaWdodCgpIHtcclxuXHRcdFx0XHR0aGlzLmxpc3RlbmVyID0gKHJlcykgPT4ge1xyXG5cdFx0XHRcdFx0Y29uc29sZS5sb2coXCLplK7nm5jpq5jluqZcIiwgcmVzLmhlaWdodClcclxuXHRcdFx0XHRcdHRoaXMua2V5Ym9hcmRIZWlnaHQgPSByZXMuaGVpZ2h0XHJcblx0XHRcdFx0XHR0aGlzLiRuZXh0VGljaygoKSA9PiB7XHJcblx0XHRcdFx0XHRcdHRoaXMuc2Nyb2xsVG9Cb3R0b20oKVxyXG5cdFx0XHRcdFx0fSlcclxuXHRcdFx0XHR9XHJcblx0XHRcdFx0dW5pLm9uS2V5Ym9hcmRIZWlnaHRDaGFuZ2UodGhpcy5saXN0ZW5lcilcclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdH07XHJcbjwvc2NyaXB0PlxyXG5cclxuPHN0eWxlIHNjb3BlZD5cclxuXHQuY2hhdC1wYWdlIHtcclxuXHRcdGZsZXg6IDE7XHJcblx0XHRmbGV4LWRpcmVjdGlvbjogY29sdW1uO1xyXG5cdFx0YmFja2dyb3VuZC1jb2xvcjogI2Y0ZjRmNDtcclxuXHR9XHJcblxyXG5cdC8qIOmhtumDqOS/oeaBr+agjyAqL1xyXG5cdC5jaGF0LWhlYWRlciB7XHJcblx0XHRmbGV4LWRpcmVjdGlvbjogcm93O1xyXG5cdFx0YWxpZ24taXRlbXM6IGNlbnRlcjtcclxuXHRcdGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG5cdFx0cGFkZGluZzogMjBycHggMzBycHg7XHJcblx0XHRiYWNrZ3JvdW5kLWNvbG9yOiAjZGJlMGYwO1xyXG5cdH1cclxuXHJcblx0LmhlYWRlci1mYWNlIHtcclxuXHRcdHdpZHRoOiA2MHJweDtcclxuXHRcdGhlaWdodDogNjBycHg7XHJcblx0XHRib3JkZXItcmFkaXVzOiA1MCU7XHJcblx0XHRtYXJnaW4tcmlnaHQ6IDIwcnB4O1xyXG5cdH1cclxuXHJcblx0LmhlYWRlci1uYW1lIHtcclxuXHRcdGZvbnQtc2l6ZTogMzJycHg7XHJcblx0XHRmb250LXdlaWdodDogYm9sZDtcclxuXHRcdGNvbG9yOiAjMzMzO1xyXG5cdH1cclxuXHJcblx0Lyog6IGK5aSp5raI5oGv5YiX6KGoICovXHJcblx0LmNoYXQtYm9keSB7XHJcblx0XHRmbGV4OiAxO1xyXG5cdFx0cGFkZGluZzogMjBycHggMjRycHg7XHJcblx0XHRiYWNrZ3JvdW5kLWNvbG9yOiAjZjlmOWY5O1xyXG5cdH1cclxuXHJcblx0Lm1zZy13cmFwcGVyIHtcclxuXHRcdGRpc3BsYXk6IGZsZXg7XHJcblx0XHRmbGV4LWRpcmVjdGlvbjogcm93O1xyXG5cdFx0YWxpZ24taXRlbXM6IGZsZXgtc3RhcnQ7XHJcblx0XHQvKiBmaXgg5aS05YOP5ZKM5rCU5rOh5Z6C55u06ZSZ5L2N6Zeu6aKYICovXHJcblx0XHRtYXJnaW4tYm90dG9tOiAyMHJweDtcclxuXHR9XHJcblxyXG5cdC5tc2ctd3JhcHBlci5mcmllbmQge1xyXG5cdFx0anVzdGlmeS1jb250ZW50OiBmbGV4LXN0YXJ0O1xyXG5cdH1cclxuXHJcblx0Lm1zZy13cmFwcGVyLnNlbGYge1xyXG5cdFx0anVzdGlmeS1jb250ZW50OiBmbGV4LWVuZDtcclxuXHR9XHJcblxyXG5cdC5tc2ctZmFjZSB7XHJcblx0XHR3aWR0aDogNjBycHg7XHJcblx0XHRoZWlnaHQ6IDYwcnB4O1xyXG5cdFx0Ym9yZGVyLXJhZGl1czogNTAlO1xyXG5cdFx0b2JqZWN0LWZpdDogY292ZXI7XHJcblx0fVxyXG5cclxuXHQubXNnLWJ1YmJsZSB7XHJcblx0XHRtYXgtd2lkdGg6IDIwMHZ3O1xyXG5cdFx0YmFja2dyb3VuZC1jb2xvcjogI2ZmZjtcclxuXHRcdGZvbnQtc2l6ZTogMjhycHg7XHJcblx0XHRsaW5lLWhlaWdodDogNDRycHg7XHJcblx0XHRwYWRkaW5nOiAyMHJweCAyOHJweDtcclxuXHRcdGJvcmRlci1yYWRpdXM6IDI0cnB4O1xyXG5cdFx0d29yZC1icmVhazogYnJlYWstd29yZDtcclxuXHRcdHdoaXRlLXNwYWNlOiBub3JtYWw7XHJcblx0fVxyXG5cclxuXHQubXNnLXdyYXBwZXIuc2VsZiAubXNnLWJ1YmJsZSB7XHJcblx0XHRiYWNrZ3JvdW5kLWNvbG9yOiAjZTBmMmZmO1xyXG5cdFx0YWxpZ24taXRlbXM6IGZsZXgtZW5kO1xyXG5cdH1cclxuXHJcblx0Lyog6L6T5YWl5qCPICovXHJcblx0LmNoYXQtaW5wdXQge1xyXG5cdFx0bWluLWhlaWdodDogMTIwcnB4O1xyXG5cdFx0YmFja2dyb3VuZC1jb2xvcjogI2ZmZjtcclxuXHRcdGJvcmRlci10b3A6IDFweCBzb2xpZCAjZTVlNWU1O1xyXG5cdFx0cGFkZGluZzogMjBycHg7XHJcblx0XHRkaXNwbGF5OiBmbGV4O1xyXG5cdFx0ZmxleC1kaXJlY3Rpb246IHJvdztcclxuXHRcdGFsaWduLWl0ZW1zOiBmbGV4LWVuZDtcclxuXHR9XHJcblxyXG5cdC5pbnB1dCB7XHJcblx0XHRmbGV4OiAxO1xyXG5cdFx0bWluLWhlaWdodDogODBycHg7XHJcblx0XHRiYWNrZ3JvdW5kLWNvbG9yOiAjZjBmMGYwO1xyXG5cdFx0Ym9yZGVyLXJhZGl1czogMzBycHg7XHJcblx0XHRwYWRkaW5nOiAxNnJweCAyNHJweDtcclxuXHRcdGZvbnQtc2l6ZTogMjhycHg7XHJcblx0XHRsaW5lLWhlaWdodDogNDBycHg7XHJcblx0XHRtYXJnaW4tcmlnaHQ6IDIwcnB4O1xyXG5cdH1cclxuXHJcblx0Lyog5LyY5YyW5oyJ6ZKu5bGF5LitICovXHJcblx0LnNlbmQtYnRuIHtcclxuXHRcdHdpZHRoOiAxMjBycHg7XHJcblx0XHRoZWlnaHQ6IDc4cnB4O1xyXG5cdFx0YmFja2dyb3VuZC1jb2xvcjogI2ZhNGI2MDtcclxuXHRcdGNvbG9yOiAjZmZmO1xyXG5cdFx0Ym9yZGVyLXJhZGl1czogMzBycHg7XHJcblx0XHRmb250LXNpemU6IDMwcnB4O1xyXG5cdFx0ZGlzcGxheTogZmxleDtcclxuXHRcdGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG5cdFx0YWxpZ24taXRlbXM6IGNlbnRlcjtcclxuXHRcdHRleHQtYWxpZ246IGNlbnRlcjtcclxuXHR9XHJcbjwvc3R5bGU+Il0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///9\n");

/***/ }),
/* 10 */
/*!*********************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/lib/format-log.js ***!
  \*********************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = formatLog;
exports.log = log;
function typof(v) {
  var s = Object.prototype.toString.call(v);
  return s.substring(8, s.length - 1);
}
function isDebugMode() {
  /* eslint-disable no-undef */
  return typeof __channelId__ === 'string' && __channelId__;
}
function jsonStringifyReplacer(k, p) {
  switch (typof(p)) {
    case 'Function':
      return 'function() { [native code] }';
    default:
      return p;
  }
}
function log(type) {
  for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
    args[_key - 1] = arguments[_key];
  }
  console[type].apply(console, args);
}
function formatLog() {
  for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
    args[_key] = arguments[_key];
  }
  var type = args.shift();
  if (isDebugMode()) {
    args.push(args.pop().replace('at ', 'uni-app:///'));
    return console[type].apply(console, args);
  }
  var msgs = args.map(function (v) {
    var type = Object.prototype.toString.call(v).toLowerCase();
    if (type === '[object object]' || type === '[object array]') {
      try {
        v = '---BEGIN:JSON---' + JSON.stringify(v, jsonStringifyReplacer) + '---END:JSON---';
      } catch (e) {
        v = type;
      }
    } else {
      if (v === null) {
        v = '---NULL---';
      } else if (v === undefined) {
        v = '---UNDEFINED---';
      } else {
        var vType = typof(v).toUpperCase();
        if (vType === 'NUMBER' || vType === 'BOOLEAN') {
          v = '---BEGIN:' + vType + '---' + v + '---END:' + vType + '---';
        } else {
          v = String(v);
        }
      }
    }
    return v;
  });
  var msg = '';
  if (msgs.length > 1) {
    var lastMsg = msgs.pop();
    msg = msgs.join('---COMMA---');
    if (lastMsg.indexOf(' at ') === 0) {
      msg += lastMsg;
    } else {
      msg += '---COMMA---' + lastMsg;
    }
  } else {
    msg = msgs[0];
  }
  console[type](msg);
}

/***/ }),
/* 11 */
/*!**********************************************************************!*\
  !*** ./node_modules/@babel/runtime/helpers/interopRequireDefault.js ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function _interopRequireDefault(obj) {
  return obj && obj.__esModule ? obj : {
    "default": obj
  };
}
module.exports = _interopRequireDefault, module.exports.__esModule = true, module.exports["default"] = module.exports;

/***/ }),
/* 12 */
/*!***************************************************************!*\
  !*** ./node_modules/@babel/runtime/helpers/defineProperty.js ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var toPropertyKey = __webpack_require__(/*! ./toPropertyKey.js */ 13);
function _defineProperty(obj, key, value) {
  key = toPropertyKey(key);
  if (key in obj) {
    Object.defineProperty(obj, key, {
      value: value,
      enumerable: true,
      configurable: true,
      writable: true
    });
  } else {
    obj[key] = value;
  }
  return obj;
}
module.exports = _defineProperty, module.exports.__esModule = true, module.exports["default"] = module.exports;

/***/ }),
/* 13 */
/*!**************************************************************!*\
  !*** ./node_modules/@babel/runtime/helpers/toPropertyKey.js ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var _typeof = __webpack_require__(/*! ./typeof.js */ 14)["default"];
var toPrimitive = __webpack_require__(/*! ./toPrimitive.js */ 15);
function toPropertyKey(t) {
  var i = toPrimitive(t, "string");
  return "symbol" == _typeof(i) ? i : i + "";
}
module.exports = toPropertyKey, module.exports.__esModule = true, module.exports["default"] = module.exports;

/***/ }),
/* 14 */
/*!*******************************************************!*\
  !*** ./node_modules/@babel/runtime/helpers/typeof.js ***!
  \*******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function _typeof(o) {
  "@babel/helpers - typeof";

  return (module.exports = _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (o) {
    return typeof o;
  } : function (o) {
    return o && "function" == typeof Symbol && o.constructor === Symbol && o !== Symbol.prototype ? "symbol" : typeof o;
  }, module.exports.__esModule = true, module.exports["default"] = module.exports), _typeof(o);
}
module.exports = _typeof, module.exports.__esModule = true, module.exports["default"] = module.exports;

/***/ }),
/* 15 */
/*!************************************************************!*\
  !*** ./node_modules/@babel/runtime/helpers/toPrimitive.js ***!
  \************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var _typeof = __webpack_require__(/*! ./typeof.js */ 14)["default"];
function toPrimitive(t, r) {
  if ("object" != _typeof(t) || !t) return t;
  var e = t[Symbol.toPrimitive];
  if (void 0 !== e) {
    var i = e.call(t, r || "default");
    if ("object" != _typeof(i)) return i;
    throw new TypeError("@@toPrimitive must return a primitive value.");
  }
  return ("string" === r ? String : Number)(t);
}
module.exports = toPrimitive, module.exports.__esModule = true, module.exports["default"] = module.exports;

/***/ }),
/* 16 */
/*!*****************************************************************************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/chat/chat.nvue?vue&type=style&index=0&id=d5c8bdb0&scoped=true&lang=css&mpType=page ***!
  \*****************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_style_index_0_id_d5c8bdb0_scoped_true_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/postcss-loader/src??ref--10-oneOf-0-2!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./chat.nvue?vue&type=style&index=0&id=d5c8bdb0&scoped=true&lang=css&mpType=page */ 17);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_style_index_0_id_d5c8bdb0_scoped_true_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_style_index_0_id_d5c8bdb0_scoped_true_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_style_index_0_id_d5c8bdb0_scoped_true_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_style_index_0_id_d5c8bdb0_scoped_true_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_chat_nvue_vue_type_style_index_0_id_d5c8bdb0_scoped_true_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),
/* 17 */
/*!******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!./node_modules/postcss-loader/src??ref--10-oneOf-0-2!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/project/Dousheng/frontend/pages/chat/chat.nvue?vue&type=style&index=0&id=d5c8bdb0&scoped=true&lang=css&mpType=page ***!
  \******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = {
  ".chat-page": {
    "": {
      "flex": [
        1,
        0,
        0,
        0
      ],
      "flexDirection": [
        "column",
        0,
        0,
        0
      ],
      "backgroundColor": [
        "#f4f4f4",
        0,
        0,
        0
      ]
    }
  },
  ".chat-header": {
    "": {
      "flexDirection": [
        "row",
        0,
        0,
        2
      ],
      "alignItems": [
        "center",
        0,
        0,
        2
      ],
      "justifyContent": [
        "center",
        0,
        0,
        2
      ],
      "paddingTop": [
        "20rpx",
        0,
        0,
        2
      ],
      "paddingRight": [
        "30rpx",
        0,
        0,
        2
      ],
      "paddingBottom": [
        "20rpx",
        0,
        0,
        2
      ],
      "paddingLeft": [
        "30rpx",
        0,
        0,
        2
      ],
      "backgroundColor": [
        "#dbe0f0",
        0,
        0,
        2
      ]
    }
  },
  ".header-face": {
    "": {
      "width": [
        "60rpx",
        0,
        0,
        3
      ],
      "height": [
        "60rpx",
        0,
        0,
        3
      ],
      "borderRadius": [
        50,
        0,
        0,
        3
      ],
      "marginRight": [
        "20rpx",
        0,
        0,
        3
      ]
    }
  },
  ".header-name": {
    "": {
      "fontSize": [
        "32rpx",
        0,
        0,
        4
      ],
      "fontWeight": [
        "bold",
        0,
        0,
        4
      ],
      "color": [
        "#333333",
        0,
        0,
        4
      ]
    }
  },
  ".chat-body": {
    "": {
      "flex": [
        1,
        0,
        0,
        6
      ],
      "paddingTop": [
        "20rpx",
        0,
        0,
        6
      ],
      "paddingRight": [
        "24rpx",
        0,
        0,
        6
      ],
      "paddingBottom": [
        "20rpx",
        0,
        0,
        6
      ],
      "paddingLeft": [
        "24rpx",
        0,
        0,
        6
      ],
      "backgroundColor": [
        "#f9f9f9",
        0,
        0,
        6
      ]
    }
  },
  ".msg-wrapper": {
    "": {
      "display": [
        "flex",
        0,
        0,
        7
      ],
      "flexDirection": [
        "row",
        0,
        0,
        7
      ],
      "alignItems": [
        "flex-start",
        0,
        0,
        7
      ],
      "marginBottom": [
        "20rpx",
        0,
        0,
        7
      ]
    },
    ".friend": {
      "justifyContent": [
        "flex-start",
        0,
        1,
        8
      ]
    },
    ".self": {
      "justifyContent": [
        "flex-end",
        0,
        1,
        9
      ]
    }
  },
  ".msg-face": {
    "": {
      "width": [
        "60rpx",
        0,
        0,
        10
      ],
      "height": [
        "60rpx",
        0,
        0,
        10
      ],
      "borderRadius": [
        50,
        0,
        0,
        10
      ],
      "objectFit": [
        "cover",
        0,
        0,
        10
      ]
    }
  },
  ".msg-bubble": {
    "": {
      "maxWidth": [
        200,
        0,
        0,
        11
      ],
      "backgroundColor": [
        "#ffffff",
        0,
        0,
        11
      ],
      "fontSize": [
        "28rpx",
        0,
        0,
        11
      ],
      "lineHeight": [
        "44rpx",
        0,
        0,
        11
      ],
      "paddingTop": [
        "20rpx",
        0,
        0,
        11
      ],
      "paddingRight": [
        "28rpx",
        0,
        0,
        11
      ],
      "paddingBottom": [
        "20rpx",
        0,
        0,
        11
      ],
      "paddingLeft": [
        "28rpx",
        0,
        0,
        11
      ],
      "borderRadius": [
        "24rpx",
        0,
        0,
        11
      ],
      "wordBreak": [
        "break-word",
        0,
        0,
        11
      ],
      "whiteSpace": [
        "normal",
        0,
        0,
        11
      ]
    },
    ".msg-wrapper.self ": {
      "backgroundColor": [
        "#e0f2ff",
        0,
        2,
        12
      ],
      "alignItems": [
        "flex-end",
        0,
        2,
        12
      ]
    }
  },
  ".chat-input": {
    "": {
      "minHeight": [
        "120rpx",
        0,
        0,
        14
      ],
      "backgroundColor": [
        "#ffffff",
        0,
        0,
        14
      ],
      "borderTopWidth": [
        "1",
        0,
        0,
        14
      ],
      "borderTopStyle": [
        "solid",
        0,
        0,
        14
      ],
      "borderTopColor": [
        "#e5e5e5",
        0,
        0,
        14
      ],
      "paddingTop": [
        "20rpx",
        0,
        0,
        14
      ],
      "paddingRight": [
        "20rpx",
        0,
        0,
        14
      ],
      "paddingBottom": [
        "20rpx",
        0,
        0,
        14
      ],
      "paddingLeft": [
        "20rpx",
        0,
        0,
        14
      ],
      "display": [
        "flex",
        0,
        0,
        14
      ],
      "flexDirection": [
        "row",
        0,
        0,
        14
      ],
      "alignItems": [
        "flex-end",
        0,
        0,
        14
      ]
    }
  },
  ".input": {
    "": {
      "flex": [
        1,
        0,
        0,
        15
      ],
      "minHeight": [
        "80rpx",
        0,
        0,
        15
      ],
      "backgroundColor": [
        "#f0f0f0",
        0,
        0,
        15
      ],
      "borderRadius": [
        "30rpx",
        0,
        0,
        15
      ],
      "paddingTop": [
        "16rpx",
        0,
        0,
        15
      ],
      "paddingRight": [
        "24rpx",
        0,
        0,
        15
      ],
      "paddingBottom": [
        "16rpx",
        0,
        0,
        15
      ],
      "paddingLeft": [
        "24rpx",
        0,
        0,
        15
      ],
      "fontSize": [
        "28rpx",
        0,
        0,
        15
      ],
      "lineHeight": [
        "40rpx",
        0,
        0,
        15
      ],
      "marginRight": [
        "20rpx",
        0,
        0,
        15
      ]
    }
  },
  ".send-btn": {
    "": {
      "width": [
        "120rpx",
        0,
        0,
        17
      ],
      "height": [
        "78rpx",
        0,
        0,
        17
      ],
      "backgroundColor": [
        "#fa4b60",
        0,
        0,
        17
      ],
      "color": [
        "#ffffff",
        0,
        0,
        17
      ],
      "borderRadius": [
        "30rpx",
        0,
        0,
        17
      ],
      "fontSize": [
        "30rpx",
        0,
        0,
        17
      ],
      "display": [
        "flex",
        0,
        0,
        17
      ],
      "justifyContent": [
        "center",
        0,
        0,
        17
      ],
      "alignItems": [
        "center",
        0,
        0,
        17
      ],
      "textAlign": [
        "center",
        0,
        0,
        17
      ]
    }
  },
  "@VERSION": 2
}

/***/ }),
/* 18 */
/*!**********************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/runtime/componentNormalizer.js ***!
  \**********************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return normalizeComponent; });
/* globals __VUE_SSR_CONTEXT__ */

// IMPORTANT: Do NOT use ES2015 features in this file (except for modules).
// This module is a runtime utility for cleaner component module output and will
// be included in the final webpack user bundle.

function normalizeComponent (
  scriptExports,
  render,
  staticRenderFns,
  functionalTemplate,
  injectStyles,
  scopeId,
  moduleIdentifier, /* server only */
  shadowMode, /* vue-cli only */
  components, // fixed by xxxxxx auto components
  renderjs // fixed by xxxxxx renderjs
) {
  // Vue.extend constructor export interop
  var options = typeof scriptExports === 'function'
    ? scriptExports.options
    : scriptExports

  // fixed by xxxxxx auto components
  if (components) {
    if (!options.components) {
      options.components = {}
    }
    var hasOwn = Object.prototype.hasOwnProperty
    for (var name in components) {
      if (hasOwn.call(components, name) && !hasOwn.call(options.components, name)) {
        options.components[name] = components[name]
      }
    }
  }
  // fixed by xxxxxx renderjs
  if (renderjs) {
    if(typeof renderjs.beforeCreate === 'function'){
			renderjs.beforeCreate = [renderjs.beforeCreate]
		}
    (renderjs.beforeCreate || (renderjs.beforeCreate = [])).unshift(function() {
      this[renderjs.__module] = this
    });
    (options.mixins || (options.mixins = [])).push(renderjs)
  }

  // render functions
  if (render) {
    options.render = render
    options.staticRenderFns = staticRenderFns
    options._compiled = true
  }

  // functional template
  if (functionalTemplate) {
    options.functional = true
  }

  // scopedId
  if (scopeId) {
    options._scopeId = 'data-v-' + scopeId
  }

  var hook
  if (moduleIdentifier) { // server build
    hook = function (context) {
      // 2.3 injection
      context =
        context || // cached call
        (this.$vnode && this.$vnode.ssrContext) || // stateful
        (this.parent && this.parent.$vnode && this.parent.$vnode.ssrContext) // functional
      // 2.2 with runInNewContext: true
      if (!context && typeof __VUE_SSR_CONTEXT__ !== 'undefined') {
        context = __VUE_SSR_CONTEXT__
      }
      // inject component styles
      if (injectStyles) {
        injectStyles.call(this, context)
      }
      // register component module identifier for async chunk inferrence
      if (context && context._registeredComponents) {
        context._registeredComponents.add(moduleIdentifier)
      }
    }
    // used by ssr in case component is cached and beforeCreate
    // never gets called
    options._ssrRegister = hook
  } else if (injectStyles) {
    hook = shadowMode
      ? function () { injectStyles.call(this, this.$root.$options.shadowRoot) }
      : injectStyles
  }

  if (hook) {
    if (options.functional) {
      // for template-only hot-reload because in that case the render fn doesn't
      // go through the normalizer
      options._injectStyles = hook
      // register for functioal component in vue file
      var originalRender = options.render
      options.render = function renderWithStyleInjection (h, context) {
        hook.call(context)
        return originalRender(h, context)
      }
    } else {
      // inject component registration as beforeCreate hook
      var existing = options.beforeCreate
      options.beforeCreate = existing
        ? [].concat(existing, hook)
        : [hook]
    }
  }

  return {
    exports: scriptExports,
    options: options
  }
}


/***/ })
/******/ ]);