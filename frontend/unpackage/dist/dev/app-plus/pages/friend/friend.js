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
/******/ 	return __webpack_require__(__webpack_require__.s = 234);
/******/ })
/************************************************************************/
/******/ ({

/***/ 1:
/*!****************************************************************!*\
  !*** D:/project/Dousheng/frontend/main.js?{"type":"appStyle"} ***!
  \****************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("Vue.prototype.__$appStyle__ = {}\nVue.prototype.__merge_style && Vue.prototype.__merge_style(__webpack_require__(/*! ./App.vue?vue&type=style&index=0&lang=css */ 2).default,Vue.prototype.__$appStyle__)\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0EsMkRBQTJELG1CQUFPLENBQUMsa0RBQTJDIiwiZmlsZSI6IjEuanMiLCJzb3VyY2VzQ29udGVudCI6WyJWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18gPSB7fVxuVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlICYmIFZ1ZS5wcm90b3R5cGUuX19tZXJnZV9zdHlsZShyZXF1aXJlKFwiLi9BcHAudnVlP3Z1ZSZ0eXBlPXN0eWxlJmluZGV4PTAmbGFuZz1jc3NcIikuZGVmYXVsdCxWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18pXG4iXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///1\n");

/***/ }),

/***/ 10:
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

/***/ 18:
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


/***/ }),

/***/ 2:
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

/***/ 234:
/*!*******************************************************************************!*\
  !*** D:/project/Dousheng/frontend/main.js?{"page":"pages%2Ffriend%2Ffriend"} ***!
  \*******************************************************************************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var uni_app_style__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! uni-app-style */ 1);\n/* harmony import */ var uni_app_style__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(uni_app_style__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony import */ var uni_polyfill__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! uni-polyfill */ 4);\n/* harmony import */ var uni_polyfill__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(uni_polyfill__WEBPACK_IMPORTED_MODULE_1__);\n/* harmony import */ var _pages_friend_friend_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./pages/friend/friend.nvue?mpType=page */ 235);\n\n        \n        \n        \n        \n        _pages_friend_friend_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].mpType = 'page'\n        _pages_friend_friend_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].route = 'pages/friend/friend'\n        _pages_friend_friend_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].el = '#root'\n        new Vue(_pages_friend_friend_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"])\n        //# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7O0FBRUEsUUFBOEI7QUFDOUIsUUFBNkI7QUFDN0IsUUFBZ0U7QUFDaEUsUUFBUSw2RUFBRztBQUNYLFFBQVEsNkVBQUc7QUFDWCxRQUFRLDZFQUFHO0FBQ1gsZ0JBQWdCLDZFQUFHIiwiZmlsZSI6IjIzNC5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuICAgICAgICBcbiAgICAgICAgaW1wb3J0ICd1bmktYXBwLXN0eWxlJ1xuICAgICAgICBpbXBvcnQgJ3VuaS1wb2x5ZmlsbCdcbiAgICAgICAgaW1wb3J0IEFwcCBmcm9tICcuL3BhZ2VzL2ZyaWVuZC9mcmllbmQubnZ1ZT9tcFR5cGU9cGFnZSdcbiAgICAgICAgQXBwLm1wVHlwZSA9ICdwYWdlJ1xuICAgICAgICBBcHAucm91dGUgPSAncGFnZXMvZnJpZW5kL2ZyaWVuZCdcbiAgICAgICAgQXBwLmVsID0gJyNyb290J1xuICAgICAgICBuZXcgVnVlKEFwcClcbiAgICAgICAgIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///234\n");

/***/ }),

/***/ 235:
/*!*************************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/friend/friend.nvue?mpType=page ***!
  \*************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./friend.nvue?vue&type=template&id=4fb7ed28&mpType=page */ 236);\n/* harmony import */ var _friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./friend.nvue?vue&type=script&lang=js&mpType=page */ 238);\n/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__) if([\"default\"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__[key]; }) }(__WEBPACK_IMPORT_KEY__));\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/runtime/componentNormalizer.js */ 18);\n\nvar renderjs\n\n\nfunction injectStyles (context) {\n  \n  if(!this.options.style){\n          this.options.style = {}\n      }\n      if(Vue.prototype.__merge_style && Vue.prototype.__$appStyle__){\n        Vue.prototype.__merge_style(Vue.prototype.__$appStyle__, this.options.style)\n      }\n      if(Vue.prototype.__merge_style){\n                Vue.prototype.__merge_style(__webpack_require__(/*! ./friend.nvue?vue&type=style&index=0&lang=css&mpType=page */ 240).default, this.options.style)\n            }else{\n                Object.assign(this.options.style,__webpack_require__(/*! ./friend.nvue?vue&type=style&index=0&lang=css&mpType=page */ 240).default)\n            }\n\n}\n\n/* normalize component */\n\nvar component = Object(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__[\"default\"])(\n  _friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__[\"default\"],\n  _friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"render\"],\n  _friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"staticRenderFns\"],\n  false,\n  null,\n  null,\n  \"4e37e231\",\n  false,\n  _friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"components\"],\n  renderjs\n)\n\ninjectStyles.call(component)\ncomponent.options.__file = \"pages/friend/friend.nvue\"\n/* harmony default export */ __webpack_exports__[\"default\"] = (component.exports);//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBK0g7QUFDL0g7QUFDc0U7QUFDTDtBQUNqRTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLDRDQUE0QyxtQkFBTyxDQUFDLG9FQUEyRDtBQUMvRyxhQUFhO0FBQ2IsaURBQWlELG1CQUFPLENBQUMsb0VBQTJEO0FBQ3BIOztBQUVBOztBQUVBO0FBQ3NLO0FBQ3RLLGdCQUFnQiw2S0FBVTtBQUMxQixFQUFFLHdGQUFNO0FBQ1IsRUFBRSw2RkFBTTtBQUNSLEVBQUUsc0dBQWU7QUFDakI7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEVBQUUsaUdBQVU7QUFDWjtBQUNBOztBQUVBO0FBQ0E7QUFDZSxnRiIsImZpbGUiOiIyMzUuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyByZW5kZXIsIHN0YXRpY1JlbmRlckZucywgcmVjeWNsYWJsZVJlbmRlciwgY29tcG9uZW50cyB9IGZyb20gXCIuL2ZyaWVuZC5udnVlP3Z1ZSZ0eXBlPXRlbXBsYXRlJmlkPTRmYjdlZDI4Jm1wVHlwZT1wYWdlXCJcbnZhciByZW5kZXJqc1xuaW1wb3J0IHNjcmlwdCBmcm9tIFwiLi9mcmllbmQubnZ1ZT92dWUmdHlwZT1zY3JpcHQmbGFuZz1qcyZtcFR5cGU9cGFnZVwiXG5leHBvcnQgKiBmcm9tIFwiLi9mcmllbmQubnZ1ZT92dWUmdHlwZT1zY3JpcHQmbGFuZz1qcyZtcFR5cGU9cGFnZVwiXG5mdW5jdGlvbiBpbmplY3RTdHlsZXMgKGNvbnRleHQpIHtcbiAgXG4gIGlmKCF0aGlzLm9wdGlvbnMuc3R5bGUpe1xuICAgICAgICAgIHRoaXMub3B0aW9ucy5zdHlsZSA9IHt9XG4gICAgICB9XG4gICAgICBpZihWdWUucHJvdG90eXBlLl9fbWVyZ2Vfc3R5bGUgJiYgVnVlLnByb3RvdHlwZS5fXyRhcHBTdHlsZV9fKXtcbiAgICAgICAgVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlKFZ1ZS5wcm90b3R5cGUuX18kYXBwU3R5bGVfXywgdGhpcy5vcHRpb25zLnN0eWxlKVxuICAgICAgfVxuICAgICAgaWYoVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlKXtcbiAgICAgICAgICAgICAgICBWdWUucHJvdG90eXBlLl9fbWVyZ2Vfc3R5bGUocmVxdWlyZShcIi4vZnJpZW5kLm52dWU/dnVlJnR5cGU9c3R5bGUmaW5kZXg9MCZsYW5nPWNzcyZtcFR5cGU9cGFnZVwiKS5kZWZhdWx0LCB0aGlzLm9wdGlvbnMuc3R5bGUpXG4gICAgICAgICAgICB9ZWxzZXtcbiAgICAgICAgICAgICAgICBPYmplY3QuYXNzaWduKHRoaXMub3B0aW9ucy5zdHlsZSxyZXF1aXJlKFwiLi9mcmllbmQubnZ1ZT92dWUmdHlwZT1zdHlsZSZpbmRleD0wJmxhbmc9Y3NzJm1wVHlwZT1wYWdlXCIpLmRlZmF1bHQpXG4gICAgICAgICAgICB9XG5cbn1cblxuLyogbm9ybWFsaXplIGNvbXBvbmVudCAqL1xuaW1wb3J0IG5vcm1hbGl6ZXIgZnJvbSBcIiEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3Z1ZS1sb2FkZXIvbGliL3J1bnRpbWUvY29tcG9uZW50Tm9ybWFsaXplci5qc1wiXG52YXIgY29tcG9uZW50ID0gbm9ybWFsaXplcihcbiAgc2NyaXB0LFxuICByZW5kZXIsXG4gIHN0YXRpY1JlbmRlckZucyxcbiAgZmFsc2UsXG4gIG51bGwsXG4gIG51bGwsXG4gIFwiNGUzN2UyMzFcIixcbiAgZmFsc2UsXG4gIGNvbXBvbmVudHMsXG4gIHJlbmRlcmpzXG4pXG5cbmluamVjdFN0eWxlcy5jYWxsKGNvbXBvbmVudClcbmNvbXBvbmVudC5vcHRpb25zLl9fZmlsZSA9IFwicGFnZXMvZnJpZW5kL2ZyaWVuZC5udnVlXCJcbmV4cG9ydCBkZWZhdWx0IGNvbXBvbmVudC5leHBvcnRzIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///235\n");

/***/ }),

/***/ 236:
/*!*******************************************************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/friend/friend.nvue?vue&type=template&id=4fb7ed28&mpType=page ***!
  \*******************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/template.js!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--8-0!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./friend.nvue?vue&type=template&id=4fb7ed28&mpType=page */ 237);
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "render", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__["render"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__["recyclableRender"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "components", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_template_id_4fb7ed28_mpType_page__WEBPACK_IMPORTED_MODULE_0__["components"]; });



/***/ }),

/***/ 237:
/*!******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/template.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--8-0!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/project/Dousheng/frontend/pages/friend/friend.nvue?vue&type=template&id=4fb7ed28&mpType=page ***!
  \******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
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
      _c("view", { staticClass: ["page"] }, [
        _c("view", { style: { height: _vm.statusBarHeight + "px" } }),
        _c(
          "view",
          {
            staticClass: ["waterfall-wrapper"],
            style: { height: _vm.screenHeight + "px" },
          },
          [
            _c(
              "waterfall",
              {
                style: { height: _vm.screenHeight + "px" },
                attrs: {
                  columnCount: "2",
                  columnWidth: "auto",
                  columnGap: "1px",
                  leftGap: "3px",
                  rightGap: "2px",
                },
              },
              [
                _vm.myUserInfo != null
                  ? _c(
                      "refresh",
                      {
                        attrs: { display: _vm.refreshing ? "show" : "hide" },
                        on: {
                          pullingdown: _vm.onpullingdown,
                          refresh: _vm.onrefresh,
                        },
                      },
                      [
                        _c(
                          "u-text",
                          {
                            staticClass: ["refresh-text"],
                            appendAsTree: true,
                            attrs: { append: "tree" },
                          },
                          [_vm._v(_vm._s(_vm.refreshText))]
                        ),
                        _c("loading-indicator"),
                      ]
                    )
                  : _vm._e(),
                _vm._l(_vm.waterList, function (vlog, index) {
                  return _c(
                    "cell",
                    {
                      key: index,
                      appendAsTree: true,
                      attrs: { append: "tree" },
                    },
                    [
                      _c(
                        "view",
                        {
                          staticClass: ["every-single-video"],
                          on: {
                            appear: function ($event) {
                              _vm.appearVlog(index)
                            },
                          },
                        },
                        [
                          _c("u-image", {
                            staticClass: ["half-cover"],
                            style: { width: _vm.vlogWidth + "px" },
                            attrs: { src: vlog.cover, mode: "widthFix" },
                            on: {
                              click: function ($event) {
                                _vm.goToVlog(vlog.vlogId)
                              },
                            },
                          }),
                          _c("view", { staticClass: ["bottom-part"] }, [
                            _c(
                              "u-text",
                              {
                                staticClass: ["bottom-vlog-content"],
                                appendAsTree: true,
                                attrs: { append: "tree" },
                              },
                              [_vm._v(_vm._s(vlog.content))]
                            ),
                            _c(
                              "view",
                              { staticClass: ["vloger-info-box"] },
                              [
                                _c("u-image", {
                                  staticClass: ["vloger-face"],
                                  attrs: { src: vlog.vlogerFace },
                                }),
                                _c(
                                  "view",
                                  { staticClass: ["vloger-name-box"] },
                                  [
                                    _c(
                                      "u-text",
                                      {
                                        staticClass: ["vloger-name"],
                                        staticStyle: { alignContent: "center" },
                                        appendAsTree: true,
                                        attrs: { append: "tree" },
                                      },
                                      [_vm._v(_vm._s(vlog.vlogerName))]
                                    ),
                                  ]
                                ),
                              ],
                              1
                            ),
                          ]),
                        ],
                        1
                      ),
                    ]
                  )
                }),
              ],
              2
            ),
          ],
          1
        ),
      ]),
    ]
  )
}
var recyclableRender = false
var staticRenderFns = []
render._withStripped = true



/***/ }),

/***/ 238:
/*!*************************************************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/friend/friend.nvue?vue&type=script&lang=js&mpType=page ***!
  \*************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/babel-loader/lib??ref--5-0!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--5-1!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./friend.nvue?vue&type=script&lang=js&mpType=page */ 239);\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__) if([\"default\"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));\n /* harmony default export */ __webpack_exports__[\"default\"] = (_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0___default.a); //# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQTBiLENBQWdCLHNkQUFHLEVBQUMiLCJmaWxlIjoiMjM4LmpzIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IG1vZCBmcm9tIFwiLSEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9iYWJlbC1sb2FkZXIvbGliL2luZGV4LmpzPz9yZWYtLTUtMCEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3dlYnBhY2stcHJlcHJvY2Vzcy1sb2FkZXIvaW5kZXguanM/P3JlZi0tNS0xIS4uLy4uLy4uLy4uLy4uL0hCdWlsZGVyWC9wbHVnaW5zL3VuaWFwcC1jbGkvbm9kZV9tb2R1bGVzL0BkY2xvdWRpby92dWUtY2xpLXBsdWdpbi11bmkvcGFja2FnZXMvdnVlLWxvYWRlci9saWIvaW5kZXguanM/P3Z1ZS1sb2FkZXItb3B0aW9ucyEuL2ZyaWVuZC5udnVlP3Z1ZSZ0eXBlPXNjcmlwdCZsYW5nPWpzJm1wVHlwZT1wYWdlXCI7IGV4cG9ydCBkZWZhdWx0IG1vZDsgZXhwb3J0ICogZnJvbSBcIi0hLi4vLi4vLi4vLi4vLi4vSEJ1aWxkZXJYL3BsdWdpbnMvdW5pYXBwLWNsaS9ub2RlX21vZHVsZXMvYmFiZWwtbG9hZGVyL2xpYi9pbmRleC5qcz8/cmVmLS01LTAhLi4vLi4vLi4vLi4vLi4vSEJ1aWxkZXJYL3BsdWdpbnMvdW5pYXBwLWNsaS9ub2RlX21vZHVsZXMvQGRjbG91ZGlvL3Z1ZS1jbGktcGx1Z2luLXVuaS9wYWNrYWdlcy93ZWJwYWNrLXByZXByb2Nlc3MtbG9hZGVyL2luZGV4LmpzPz9yZWYtLTUtMSEuLi8uLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3Z1ZS1sb2FkZXIvbGliL2luZGV4LmpzPz92dWUtbG9hZGVyLW9wdGlvbnMhLi9mcmllbmQubnZ1ZT92dWUmdHlwZT1zY3JpcHQmbGFuZz1qcyZtcFR5cGU9cGFnZVwiIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///238\n");

/***/ }),

/***/ 239:
/*!****************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib??ref--5-0!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--5-1!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/project/Dousheng/frontend/pages/friend/friend.nvue?vue&type=script&lang=js&mpType=page ***!
  \****************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
eval("/* WEBPACK VAR INJECTION */(function(__f__) {\n\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\nexports.default = void 0;\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n\nvar system = uni.getSystemInfoSync();\nvar app = getApp();\nvar _default = {\n  data: function data() {\n    return {\n      screenHeight: 0,\n      statusBarHeight: 0,\n      vlogWidth: 0,\n      // waterList: getApp().getDefaultVlogList()\n\n      waterList: [],\n      page: 0,\n      totalPage: 0,\n      refreshing: false,\n      refreshText: \"下拉刷新中...\",\n      myUserInfo: null\n    };\n  },\n  onLoad: function onLoad() {\n    this.statusBarHeight = system.statusBarHeight;\n    var screenWidth = system.screenWidth;\n    this.vlogWidth = (screenWidth - 10) / 2;\n\n    // var screenHeight = system.safeArea.bottom + 50;\n    var screenHeight = system.safeArea.bottom - 10;\n    this.screenHeight = screenHeight;\n    // console.log(this.screenWidth);\n    this.myUserInfo = app.getUserInfoSession();\n  },\n  onShow: function onShow() {\n    this.page = 0;\n    this.fetchList(0);\n  },\n  methods: {\n    // 下拉刷新的过程中，会触发\n    onpullingdown: function onpullingdown(e) {\n      // console.log(\"222 -- \" + this.onpullingdown);\n    },\n    onrefresh: function onrefresh(e) {\n      var _this = this;\n      // console.log(\"111 -- \" + this.refreshing);\n      var me = this;\n      this.refreshing = true;\n      setTimeout(function () {\n        _this.refreshing = false;\n        _this.refreshText = '下拉刷新中...';\n        uni.stopPullDownRefresh();\n      }, 300);\n\n      // 通过list组件的下拉刷新触发当前所在页面的下拉刷新\n      this.page = 0;\n      this.fetchList(0);\n      uni.startPullDownRefresh();\n    },\n    loadMore: function loadMore() {\n      if (this.page >= this.totalPage) {\n        return;\n      } else {\n        this.fetchList(this.page);\n      }\n    },\n    fetchList: function fetchList(page) {\n      uni.stopPullDownRefresh();\n      var me = this;\n      var myUserInfo = app.getUserInfoSession();\n      if (myUserInfo == null) {\n        me.waterList = [];\n        return;\n      }\n      page = page + 1;\n      var userId = getApp().getUserInfoSession().id;\n      var serverUrl = app.globalData.serverUrl;\n      uni.request({\n        method: \"GET\",\n        header: {\n          headerUserId: userId,\n          headerUserToken: app.getUserSessionToken()\n        },\n        url: serverUrl + \"/vlog/friendList?myId=\" + userId + \"&page=\" + page + \"&pageSize=10\",\n        success: function success(result) {\n          __f__(\"log\", result, \" at pages/friend/friend.nvue:196\");\n          if (result.data.code == \"0\") {\n            var waterList = result.data.data.rows;\n            var totalPage = result.data.data.total;\n            if (page == 1) {\n              me.waterList = [];\n            }\n            me.waterList = me.waterList.concat(waterList);\n            me.page = page;\n            me.totalPage = totalPage;\n          }\n          uni.stopPullDownRefresh();\n        }\n      });\n    },\n    goToVlog: function goToVlog(vlogId) {\n      uni.navigateTo({\n        url: \"../vlog/vlog?vlogId=\" + vlogId\n      });\n    },\n    // 每个vlog出现都会触发\n    appearVlog: function appearVlog(index, e) {\n      var me = this;\n      // console.log(\"appearVlog --- e=\" + JSON.stringify(e));\n      // console.log(\"appearVlog --- index=\" + index);\n      // 如果最后一个vlog出现，则加载更多\n      if (index == me.waterList.length - 1) {\n        // var newList = getApp().getDefaultVlogList();\n        // me.waterList = me.waterList.concat(newList);\n        me.loadMore();\n      }\n    }\n  }\n};\nexports.default = _default;\n/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/vue-cli-plugin-uni/lib/format-log.js */ 10)[\"default\"]))//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInVuaS1hcHA6Ly8vcGFnZXMvZnJpZW5kL2ZyaWVuZC5udnVlIl0sIm5hbWVzIjpbImRhdGEiLCJzY3JlZW5IZWlnaHQiLCJzdGF0dXNCYXJIZWlnaHQiLCJ2bG9nV2lkdGgiLCJ3YXRlckxpc3QiLCJwYWdlIiwidG90YWxQYWdlIiwicmVmcmVzaGluZyIsInJlZnJlc2hUZXh0IiwibXlVc2VySW5mbyIsIm9uTG9hZCIsIm9uU2hvdyIsIm1ldGhvZHMiLCJvbnB1bGxpbmdkb3duIiwib25yZWZyZXNoIiwic2V0VGltZW91dCIsInVuaSIsImxvYWRNb3JlIiwiZmV0Y2hMaXN0IiwibWUiLCJtZXRob2QiLCJoZWFkZXIiLCJoZWFkZXJVc2VySWQiLCJoZWFkZXJVc2VyVG9rZW4iLCJ1cmwiLCJzdWNjZXNzIiwiZ29Ub1Zsb2ciLCJhcHBlYXJWbG9nIl0sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQThHQTtBQUNBO0FBQUEsZUFDQTtFQUNBQTtJQUNBO01BQ0FDO01BQ0FDO01BQ0FDO01BQ0E7O01BRUFDO01BQ0FDO01BQ0FDO01BRUFDO01BQ0FDO01BQ0FDO0lBQ0E7RUFDQTtFQUNBQztJQUNBO0lBQ0E7SUFDQTs7SUFFQTtJQUNBO0lBQ0E7SUFDQTtJQUNBO0VBQ0E7RUFDQUM7SUFDQTtJQUNBO0VBQ0E7RUFDQUM7SUFDQTtJQUNBQztNQUNBO0lBQUEsQ0FDQTtJQUNBQztNQUFBO01BQ0E7TUFDQTtNQUVBO01BQ0FDO1FBQ0E7UUFDQTtRQUNBQztNQUNBOztNQUVBO01BQ0E7TUFDQTtNQUNBQTtJQUNBO0lBRUFDO01BQ0E7UUFDQTtNQUNBO1FBQ0E7TUFDQTtJQUNBO0lBQ0FDO01BQ0FGO01BQ0E7TUFFQTtNQUNBO1FBQ0FHO1FBQ0E7TUFDQTtNQUVBZDtNQUVBO01BQ0E7TUFDQVc7UUFDQUk7UUFDQUM7VUFDQUM7VUFDQUM7UUFDQTtRQUNBQztRQUNBQztVQUNBO1VBRUE7WUFDQTtZQUNBO1lBRUE7Y0FDQU47WUFDQTtZQUVBQTtZQUNBQTtZQUNBQTtVQUNBO1VBQ0FIO1FBQ0E7TUFDQTtJQUNBO0lBRUFVO01BQ0FWO1FBQ0FRO01BQ0E7SUFDQTtJQUVBO0lBQ0FHO01BQ0E7TUFDQTtNQUNBO01BQ0E7TUFDQTtRQUNBO1FBQ0E7UUFDQVI7TUFDQTtJQUNBO0VBQ0E7QUFDQTtBQUFBLDJCIiwiZmlsZSI6IjIzOS5qcyIsInNvdXJjZXNDb250ZW50IjpbIjxzdHlsZT5cclxuLnBhZ2Uge1xyXG5cdHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuXHRsZWZ0OiAwO1xyXG5cdHJpZ2h0OiAwO1xyXG5cdHRvcDogMDtcclxuXHRib3R0b206IDA7XHJcblx0YmFja2dyb3VuZC1jb2xvcjogIzAwMDAwMDtcclxufVxyXG5cclxuLndhdGVyZmFsbC13cmFwcGVyIHtcclxuXHRiYWNrZ3JvdW5kLWNvbG9yOiAjMDAwO1xyXG59XHJcbi5ldmVyeS1zaW5nbGUtdmlkZW8ge1xyXG5cdGRpc3BsYXk6IGZsZXg7XHJcblx0ZmxleC1kaXJlY3Rpb246IGNvbHVtbjtcclxuXHRtYXJnaW4tdG9wOiA1cHg7XHJcbn1cclxuLmhhbGYtY292ZXIge1xyXG5cdGJvcmRlci10b3AtbGVmdC1yYWRpdXM6IDVweDsgXHJcblx0Ym9yZGVyLXRvcC1yaWdodC1yYWRpdXM6IDVweDtcclxufVxyXG4udmxvZ2VyLW5hbWUge1xyXG5cdGZvbnQtc2l6ZTogMTJweDtcclxuXHRjb2xvcjogI0ZGRkZGRjtcclxuXHRsaW5lczogMjtcclxuXHR0ZXh0LW92ZXJmbG93OiBlbGxpcHNpcztcclxuXHRtYXJnaW4tbGVmdDogMTBweDtcclxufVxyXG4uYm90dG9tLXBhcnQge1xyXG5cdHBhZGRpbmc6IDVweDtcclxuXHRtYXJnaW4tdG9wOiAycHg7XHJcbn1cclxuLmJvdHRvbS12bG9nLWNvbnRlbnQge1xyXG5cdGZvbnQtc2l6ZTogMTJweDtcclxuXHRjb2xvcjogI0ZGRkZGRjtcclxuXHRsaW5lczogMjtcclxuXHR0ZXh0LW92ZXJmbG93OiBlbGxpcHNpcztcclxufVxyXG4udmxvZ2VyLWluZm8tYm94IHtcclxuXHRtYXJnaW46IDVweCAwO1xyXG5cdGRpc3BsYXk6IGZsZXg7XHJcblx0ZmxleC1kaXJlY3Rpb246IHJvdztcclxufVxyXG4udmxvZ2VyLWZhY2Uge1xyXG5cdHdpZHRoOiA1MHJweDtcclxuXHRoZWlnaHQ6IDUwcnB4O1xyXG5cdGJvcmRlci1yYWRpdXM6IDEwcHg7XHJcbn1cclxuLnZsb2dlci1uYW1lLWJveCB7XHJcblx0ZGlzcGxheTogZmxleDtcclxuXHRmbGV4LWRpcmVjdGlvbjogY29sdW1uO1xyXG5cdGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG59XHJcbi5yZWZyZXNoLXRleHQge1xyXG5cdGNvbG9yOiAjRkZGRkZGO1xyXG5cdHdpZHRoOiA3NTBycHg7XHJcblx0YWxpZ24taXRlbXM6IGNlbnRlcjtcclxuXHR0ZXh0LWFsaWduOiBjZW50ZXI7XHJcblx0aGVpZ2h0OiA4MHJweDtcclxufVxyXG48L3N0eWxlPlxyXG5cclxuPHRlbXBsYXRlPlxyXG5cdDx2aWV3IGNsYXNzPVwicGFnZVwiPlxyXG5cdFx0PHZpZXcgOnN0eWxlPVwie2hlaWdodDogc3RhdHVzQmFySGVpZ2h0ICsgJ3B4J31cIj5cclxuXHRcdCAgPCEtLSDov5nph4zmmK/nirbmgIHmoI8sIOavj+S4qumhtemdoumDvemcgOimgeacie+8jOebrueahOS4jeiuqemhtemdouimhueblueKtuaAgeagjyAtLT5cclxuXHRcdDwvdmlldz5cclxuXHRcdDx2aWV3IGNsYXNzPVwid2F0ZXJmYWxsLXdyYXBwZXJcIiA6c3R5bGU9XCJ7aGVpZ2h0OiBzY3JlZW5IZWlnaHQgKyAncHgnfVwiPlxyXG5cdFx0XHQ8d2F0ZXJmYWxsIFxyXG5cdFx0XHRcdDpzdHlsZT1cIntoZWlnaHQ6IHNjcmVlbkhlaWdodCArICdweCd9XCIgXHJcblx0XHRcdFx0Y29sdW1uLWNvdW50PVwiMlwiIFxyXG5cdFx0XHRcdGNvbHVtbi13aWR0aD1cImF1dG9cIiBcclxuXHRcdFx0XHRjb2x1bW4tZ2FwPVwiMXB4XCIgXHJcblx0XHRcdFx0bGVmdC1nYXA9XCIzcHhcIiBcclxuXHRcdFx0XHRyaWdodC1nYXA9XCIycHhcIj5cclxuXHRcdFx0XHQ8cmVmcmVzaCBcclxuXHRcdFx0XHRcdHYtaWY9XCJteVVzZXJJbmZvICE9IG51bGxcIlxyXG5cdFx0XHRcdFx0QHB1bGxpbmdkb3duPVwib25wdWxsaW5nZG93blwiIFxyXG5cdFx0XHRcdFx0QHJlZnJlc2g9XCJvbnJlZnJlc2hcIiBcclxuXHRcdFx0XHRcdDpkaXNwbGF5PVwicmVmcmVzaGluZyA/ICdzaG93JyA6ICdoaWRlJ1wiPlxyXG5cdFx0XHRcdFx0XHQ8dGV4dCBjbGFzcz1cInJlZnJlc2gtdGV4dFwiIHN0eWxlPVwiXCI+e3tyZWZyZXNoVGV4dH19PC90ZXh0PlxyXG5cdFx0XHRcdFx0XHQ8bG9hZGluZy1pbmRpY2F0b3I+PC9sb2FkaW5nLWluZGljYXRvcj5cclxuXHRcdFx0XHQ8L3JlZnJlc2g+XHJcblx0XHRcdCAgICA8Y2VsbCB2LWZvcj1cIih2bG9nLCBpbmRleCkgaW4gd2F0ZXJMaXN0XCIgOmtleT1cImluZGV4XCIgPlxyXG5cdFx0XHRcdFx0PHZpZXcgY2xhc3M9XCJldmVyeS1zaW5nbGUtdmlkZW9cIiBAYXBwZWFyPVwiYXBwZWFyVmxvZyhpbmRleClcIj5cclxuXHRcdFx0XHRcdFx0PGltYWdlIFxyXG5cdFx0XHRcdFx0XHRcdDpzcmM9XCJ2bG9nLmNvdmVyXCIgXHJcblx0XHRcdFx0XHRcdFx0bW9kZT1cIndpZHRoRml4XCIgXHJcblx0XHRcdFx0XHRcdFx0Y2xhc3M9XCJoYWxmLWNvdmVyXCJcclxuXHRcdFx0XHRcdFx0XHQ6c3R5bGU9XCJ7d2lkdGg6IHZsb2dXaWR0aCArICdweCd9XCJcclxuXHRcdFx0XHRcdFx0XHRAY2xpY2s9XCJnb1RvVmxvZyh2bG9nLnZsb2dJZClcIj5cclxuXHRcdFx0XHRcdFx0PHZpZXcgY2xhc3M9XCJib3R0b20tcGFydFwiPlxyXG5cdFx0XHRcdFx0XHRcdDx0ZXh0IGNsYXNzPVwiYm90dG9tLXZsb2ctY29udGVudFwiPnt7dmxvZy5jb250ZW50fX08L3RleHQ+XHJcblx0XHRcdFx0XHRcdFx0PHZpZXcgY2xhc3M9XCJ2bG9nZXItaW5mby1ib3hcIj5cclxuXHRcdFx0XHRcdFx0XHRcdDxpbWFnZSA6c3JjPVwidmxvZy52bG9nZXJGYWNlXCIgY2xhc3M9XCJ2bG9nZXItZmFjZVwiPjwvaW1hZ2U+XHJcblx0XHRcdFx0XHRcdFx0XHQ8dmlldyBjbGFzcz1cInZsb2dlci1uYW1lLWJveFwiPlxyXG5cdFx0XHRcdFx0XHRcdFx0XHQ8dGV4dCBjbGFzcz1cInZsb2dlci1uYW1lXCIgc3R5bGU9XCJhbGlnbi1jb250ZW50OiBjZW50ZXI7XCI+e3t2bG9nLnZsb2dlck5hbWV9fTwvdGV4dD5cclxuXHRcdFx0XHRcdFx0XHRcdDwvdmlldz5cclxuXHRcdFx0XHRcdFx0XHQ8L3ZpZXc+XHJcblx0XHRcdFx0XHRcdDwvdmlldz5cclxuXHRcdFx0XHRcdFx0XHJcblx0XHRcdFx0XHQ8L3ZpZXc+XHJcblx0XHRcdCAgICA8L2NlbGw+XHJcblx0XHRcdCAgPC93YXRlcmZhbGw+XHJcblx0XHQ8L3ZpZXc+XHJcblx0PC92aWV3PlxyXG48L3RlbXBsYXRlPlxyXG5cclxuPHNjcmlwdD5cclxuXHR2YXIgc3lzdGVtID0gdW5pLmdldFN5c3RlbUluZm9TeW5jKCk7XHJcblx0dmFyIGFwcCA9IGdldEFwcCgpO1xyXG5cdGV4cG9ydCBkZWZhdWx0IHtcclxuXHRcdGRhdGEoKSB7XHJcblx0XHRcdHJldHVybiB7XHJcblx0XHRcdFx0c2NyZWVuSGVpZ2h0OiAwLFxyXG5cdFx0XHRcdHN0YXR1c0JhckhlaWdodDogMCxcclxuXHRcdFx0XHR2bG9nV2lkdGg6MCxcclxuXHRcdFx0XHQvLyB3YXRlckxpc3Q6IGdldEFwcCgpLmdldERlZmF1bHRWbG9nTGlzdCgpXHJcblx0XHRcdFx0XHJcblx0XHRcdFx0d2F0ZXJMaXN0OiBbXSxcclxuXHRcdFx0XHRwYWdlOiAwLFxyXG5cdFx0XHRcdHRvdGFsUGFnZTogMCxcclxuXHRcdFx0XHRcclxuXHRcdFx0XHRyZWZyZXNoaW5nOiBmYWxzZSxcclxuXHRcdFx0XHRyZWZyZXNoVGV4dDogXCLkuIvmi4nliLfmlrDkuK0uLi5cIixcclxuXHRcdFx0XHRteVVzZXJJbmZvOiBudWxsXHJcblx0XHRcdH1cclxuXHRcdH0sXHJcblx0XHRvbkxvYWQoKSB7XHJcblx0XHRcdHRoaXMuc3RhdHVzQmFySGVpZ2h0ID0gc3lzdGVtLnN0YXR1c0JhckhlaWdodDtcclxuXHRcdFx0dmFyIHNjcmVlbldpZHRoID0gc3lzdGVtLnNjcmVlbldpZHRoO1xyXG5cdFx0XHR0aGlzLnZsb2dXaWR0aCA9IChzY3JlZW5XaWR0aC0xMCkvMjtcclxuXHRcdFx0XHJcblx0XHRcdC8vIHZhciBzY3JlZW5IZWlnaHQgPSBzeXN0ZW0uc2FmZUFyZWEuYm90dG9tICsgNTA7XHJcblx0XHRcdHZhciBzY3JlZW5IZWlnaHQgPSBzeXN0ZW0uc2FmZUFyZWEuYm90dG9tIC0gMTA7XHJcblx0XHRcdHRoaXMuc2NyZWVuSGVpZ2h0ID0gc2NyZWVuSGVpZ2h0O1xyXG5cdFx0XHQvLyBjb25zb2xlLmxvZyh0aGlzLnNjcmVlbldpZHRoKTtcclxuXHRcdFx0dGhpcy5teVVzZXJJbmZvID0gYXBwLmdldFVzZXJJbmZvU2Vzc2lvbigpO1xyXG5cdFx0fSxcclxuXHRcdG9uU2hvdygpIHtcclxuXHRcdFx0dGhpcy5wYWdlID0gMDtcclxuXHRcdFx0dGhpcy5mZXRjaExpc3QoMCk7XHJcblx0XHR9LFxyXG5cdFx0bWV0aG9kczoge1xyXG5cdFx0XHQvLyDkuIvmi4nliLfmlrDnmoTov4fnqIvkuK3vvIzkvJrop6blj5FcclxuXHRcdFx0b25wdWxsaW5nZG93bihlKSB7XHJcblx0XHRcdFx0Ly8gY29uc29sZS5sb2coXCIyMjIgLS0gXCIgKyB0aGlzLm9ucHVsbGluZ2Rvd24pO1xyXG5cdFx0XHR9LFxyXG5cdFx0XHRvbnJlZnJlc2goZSkge1xyXG5cdFx0XHRcdC8vIGNvbnNvbGUubG9nKFwiMTExIC0tIFwiICsgdGhpcy5yZWZyZXNoaW5nKTtcclxuXHRcdFx0XHR2YXIgbWUgPSB0aGlzO1xyXG5cdFx0XHRcdFxyXG5cdFx0XHRcdHRoaXMucmVmcmVzaGluZyA9IHRydWU7XHJcblx0XHRcdFx0c2V0VGltZW91dCgoKSA9PiB7IFxyXG5cdFx0XHRcdFx0dGhpcy5yZWZyZXNoaW5nID0gZmFsc2U7XHJcblx0XHRcdFx0XHR0aGlzLnJlZnJlc2hUZXh0ID0gJ+S4i+aLieWIt+aWsOS4rS4uLidcclxuXHRcdFx0XHRcdHVuaS5zdG9wUHVsbERvd25SZWZyZXNoKCk7XHJcblx0XHRcdFx0fSwgMzAwKVxyXG5cdFx0XHRcdFx0XHRcdFxyXG5cdFx0XHRcdC8vIOmAmui/h2xpc3Tnu4Tku7bnmoTkuIvmi4nliLfmlrDop6blj5HlvZPliY3miYDlnKjpobXpnaLnmoTkuIvmi4nliLfmlrBcclxuXHRcdFx0XHR0aGlzLnBhZ2UgPSAwO1xyXG5cdFx0XHRcdHRoaXMuZmV0Y2hMaXN0KDApO1xyXG5cdFx0XHRcdHVuaS5zdGFydFB1bGxEb3duUmVmcmVzaCgpO1xyXG5cdFx0XHR9LFxyXG5cdFx0XHRcclxuXHRcdFx0bG9hZE1vcmUoKSB7XHJcblx0XHRcdFx0aWYgKHRoaXMucGFnZSA+PSB0aGlzLnRvdGFsUGFnZSkge1xyXG5cdFx0XHRcdFx0cmV0dXJuO1xyXG5cdFx0XHRcdH0gZWxzZSB7XHJcblx0XHRcdFx0XHR0aGlzLmZldGNoTGlzdCh0aGlzLnBhZ2UpO1xyXG5cdFx0XHRcdH1cclxuXHRcdFx0fSxcclxuXHRcdFx0ZmV0Y2hMaXN0KHBhZ2UpIHtcclxuXHRcdFx0XHR1bmkuc3RvcFB1bGxEb3duUmVmcmVzaCgpO1xyXG5cdFx0XHRcdHZhciBtZSA9IHRoaXM7XHJcblx0XHRcdFx0XHJcblx0XHRcdFx0dmFyIG15VXNlckluZm8gPSBhcHAuZ2V0VXNlckluZm9TZXNzaW9uKCk7XHJcblx0XHRcdFx0aWYgKG15VXNlckluZm8gPT0gbnVsbCkge1xyXG5cdFx0XHRcdFx0bWUud2F0ZXJMaXN0ID0gW107XHJcblx0XHRcdFx0XHRyZXR1cm47XHJcblx0XHRcdFx0fVxyXG5cdFx0XHRcdFxyXG5cdFx0XHRcdHBhZ2UgPSBwYWdlICsgMTtcclxuXHRcdFx0XHRcclxuXHRcdFx0XHR2YXIgdXNlcklkID0gZ2V0QXBwKCkuZ2V0VXNlckluZm9TZXNzaW9uKCkuaWQ7XHJcblx0XHRcdFx0dmFyIHNlcnZlclVybCA9IGFwcC5nbG9iYWxEYXRhLnNlcnZlclVybDtcclxuXHRcdFx0XHR1bmkucmVxdWVzdCh7XHJcblx0XHRcdFx0XHRtZXRob2Q6IFwiR0VUXCIsXHJcblx0XHRcdFx0XHRoZWFkZXI6IHtcclxuXHRcdFx0XHRcdFx0aGVhZGVyVXNlcklkOiB1c2VySWQsXHJcblx0XHRcdFx0XHRcdGhlYWRlclVzZXJUb2tlbjogYXBwLmdldFVzZXJTZXNzaW9uVG9rZW4oKVxyXG5cdFx0XHRcdFx0fSxcclxuXHRcdFx0XHRcdHVybDogc2VydmVyVXJsICsgXCIvdmxvZy9mcmllbmRMaXN0P215SWQ9XCIgKyB1c2VySWQgKyBcIiZwYWdlPVwiICsgcGFnZSArIFwiJnBhZ2VTaXplPTEwXCIsXHJcblx0XHRcdFx0XHRzdWNjZXNzKHJlc3VsdCkge1xyXG5cdFx0XHRcdFx0XHRjb25zb2xlLmxvZyhyZXN1bHQpO1xyXG5cdFx0XHRcdFx0XHRcclxuXHRcdFx0XHRcdFx0aWYgKHJlc3VsdC5kYXRhLmNvZGUgPT0gXCIwXCIpIHtcclxuXHRcdFx0XHRcdFx0XHR2YXIgd2F0ZXJMaXN0ID0gcmVzdWx0LmRhdGEuZGF0YS5yb3dzO1xyXG5cdFx0XHRcdFx0XHRcdHZhciB0b3RhbFBhZ2UgPSByZXN1bHQuZGF0YS5kYXRhLnRvdGFsO1xyXG5cdFx0XHRcdFx0XHRcdFxyXG5cdFx0XHRcdFx0XHRcdGlmIChwYWdlID09IDEpIHtcclxuXHRcdFx0XHRcdFx0XHRcdG1lLndhdGVyTGlzdCA9IFtdO1xyXG5cdFx0XHRcdFx0XHRcdH1cclxuXHRcdFx0XHRcdFx0XHRcclxuXHRcdFx0XHRcdFx0XHRtZS53YXRlckxpc3QgPSBtZS53YXRlckxpc3QuY29uY2F0KHdhdGVyTGlzdCk7XHJcblx0XHRcdFx0XHRcdFx0bWUucGFnZSA9IHBhZ2U7XHJcblx0XHRcdFx0XHRcdFx0bWUudG90YWxQYWdlID0gdG90YWxQYWdlO1xyXG5cdFx0XHRcdFx0XHR9IFxyXG5cdFx0XHRcdFx0XHR1bmkuc3RvcFB1bGxEb3duUmVmcmVzaCgpO1xyXG5cdFx0XHRcdFx0fVxyXG5cdFx0XHRcdH0pO1xyXG5cdFx0XHR9LFxyXG5cdFx0XHRcclxuXHRcdFx0Z29Ub1Zsb2codmxvZ0lkKSB7XHJcblx0XHRcdFx0dW5pLm5hdmlnYXRlVG8oe1xyXG5cdFx0XHRcdFx0dXJsOiBcIi4uL3Zsb2cvdmxvZz92bG9nSWQ9XCIgKyB2bG9nSWRcclxuXHRcdFx0XHR9KVxyXG5cdFx0XHR9LFxyXG5cdFx0XHRcclxuXHRcdFx0Ly8g5q+P5LiqdmxvZ+WHuueOsOmDveS8muinpuWPkVxyXG5cdFx0XHRhcHBlYXJWbG9nKGluZGV4LCBlKSB7XHJcblx0XHRcdFx0dmFyIG1lID0gdGhpcztcclxuXHRcdFx0XHQvLyBjb25zb2xlLmxvZyhcImFwcGVhclZsb2cgLS0tIGU9XCIgKyBKU09OLnN0cmluZ2lmeShlKSk7XHJcblx0XHRcdFx0Ly8gY29uc29sZS5sb2coXCJhcHBlYXJWbG9nIC0tLSBpbmRleD1cIiArIGluZGV4KTtcclxuXHRcdFx0XHQvLyDlpoLmnpzmnIDlkI7kuIDkuKp2bG9n5Ye6546w77yM5YiZ5Yqg6L295pu05aSaXHJcblx0XHRcdFx0aWYoaW5kZXggPT0gKG1lLndhdGVyTGlzdC5sZW5ndGggLSAxKSkge1xyXG5cdFx0XHRcdFx0Ly8gdmFyIG5ld0xpc3QgPSBnZXRBcHAoKS5nZXREZWZhdWx0VmxvZ0xpc3QoKTtcclxuXHRcdFx0XHRcdC8vIG1lLndhdGVyTGlzdCA9IG1lLndhdGVyTGlzdC5jb25jYXQobmV3TGlzdCk7XHJcblx0XHRcdFx0XHRtZS5sb2FkTW9yZSgpO1xyXG5cdFx0XHRcdH1cclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdH1cclxuPC9zY3JpcHQ+XHJcblxyXG4iXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///239\n");

/***/ }),

/***/ 240:
/*!*********************************************************************************************************!*\
  !*** D:/project/Dousheng/frontend/pages/friend/friend.nvue?vue&type=style&index=0&lang=css&mpType=page ***!
  \*********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_style_index_0_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/postcss-loader/src??ref--10-oneOf-0-2!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!../../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./friend.nvue?vue&type=style&index=0&lang=css&mpType=page */ 241);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_style_index_0_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_style_index_0_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_style_index_0_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_style_index_0_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_friend_nvue_vue_type_style_index_0_lang_css_mpType_page__WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),

/***/ 241:
/*!**********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!./node_modules/postcss-loader/src??ref--10-oneOf-0-2!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/project/Dousheng/frontend/pages/friend/friend.nvue?vue&type=style&index=0&lang=css&mpType=page ***!
  \**********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = {
  ".page": {
    "": {
      "position": [
        "absolute",
        0,
        0,
        0
      ],
      "left": [
        0,
        0,
        0,
        0
      ],
      "right": [
        0,
        0,
        0,
        0
      ],
      "top": [
        0,
        0,
        0,
        0
      ],
      "bottom": [
        0,
        0,
        0,
        0
      ],
      "backgroundColor": [
        "#000000",
        0,
        0,
        0
      ]
    }
  },
  ".waterfall-wrapper": {
    "": {
      "backgroundColor": [
        "#000000",
        0,
        0,
        1
      ]
    }
  },
  ".every-single-video": {
    "": {
      "display": [
        "flex",
        0,
        0,
        2
      ],
      "flexDirection": [
        "column",
        0,
        0,
        2
      ],
      "marginTop": [
        "5",
        0,
        0,
        2
      ]
    }
  },
  ".half-cover": {
    "": {
      "borderTopLeftRadius": [
        "5",
        0,
        0,
        3
      ],
      "borderTopRightRadius": [
        "5",
        0,
        0,
        3
      ]
    }
  },
  ".vloger-name": {
    "": {
      "fontSize": [
        "12",
        0,
        0,
        4
      ],
      "color": [
        "#FFFFFF",
        0,
        0,
        4
      ],
      "lines": [
        2,
        0,
        0,
        4
      ],
      "textOverflow": [
        "ellipsis",
        0,
        0,
        4
      ],
      "marginLeft": [
        "10",
        0,
        0,
        4
      ]
    }
  },
  ".bottom-part": {
    "": {
      "paddingTop": [
        "5",
        0,
        0,
        5
      ],
      "paddingRight": [
        "5",
        0,
        0,
        5
      ],
      "paddingBottom": [
        "5",
        0,
        0,
        5
      ],
      "paddingLeft": [
        "5",
        0,
        0,
        5
      ],
      "marginTop": [
        "2",
        0,
        0,
        5
      ]
    }
  },
  ".bottom-vlog-content": {
    "": {
      "fontSize": [
        "12",
        0,
        0,
        6
      ],
      "color": [
        "#FFFFFF",
        0,
        0,
        6
      ],
      "lines": [
        2,
        0,
        0,
        6
      ],
      "textOverflow": [
        "ellipsis",
        0,
        0,
        6
      ]
    }
  },
  ".vloger-info-box": {
    "": {
      "marginTop": [
        "5",
        0,
        0,
        7
      ],
      "marginRight": [
        0,
        0,
        0,
        7
      ],
      "marginBottom": [
        "5",
        0,
        0,
        7
      ],
      "marginLeft": [
        0,
        0,
        0,
        7
      ],
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
      ]
    }
  },
  ".vloger-face": {
    "": {
      "width": [
        "50rpx",
        0,
        0,
        8
      ],
      "height": [
        "50rpx",
        0,
        0,
        8
      ],
      "borderRadius": [
        "10",
        0,
        0,
        8
      ]
    }
  },
  ".vloger-name-box": {
    "": {
      "display": [
        "flex",
        0,
        0,
        9
      ],
      "flexDirection": [
        "column",
        0,
        0,
        9
      ],
      "justifyContent": [
        "center",
        0,
        0,
        9
      ]
    }
  },
  ".refresh-text": {
    "": {
      "color": [
        "#FFFFFF",
        0,
        0,
        10
      ],
      "width": [
        "750rpx",
        0,
        0,
        10
      ],
      "alignItems": [
        "center",
        0,
        0,
        10
      ],
      "textAlign": [
        "center",
        0,
        0,
        10
      ],
      "height": [
        "80rpx",
        0,
        0,
        10
      ]
    }
  },
  "@VERSION": 2
}

/***/ }),

/***/ 3:
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

/***/ 4:
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

/***/ })

/******/ });