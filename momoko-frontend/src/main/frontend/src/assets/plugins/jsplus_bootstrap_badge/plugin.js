(function(){function ak(){return"tinymce";}function g(aG){return I()!="3"&&aG.inline;}function z(aG){return aG.id.replace(/\[/,"_").replace(/\]/,"_");}function i(aH){if(I()=="3"||!g(aH)){return aH.getContainer();}var aG=window.document.getElementById(aH.theme.panel._id);return aG;}function c(aG){return aG.getDoc();}function J(aG){return aG.getContent();}function M(aH,aG){aH.setContent(aG);}function U(aG){if(tinymce.activeEditor==null||tinymce.activeEditor.selection==null){return null;}return tinymce.activeEditor.selection.getNode();}function P(){return tinymce.baseURL;}function aw(){return h("jsplus_bootstrap_badge");}function h(aL){for(var aJ=0;aJ<tinymce.editors.length;aJ++){var aK=tinymce.editors[aJ];var aI=O(aK,"external_plugins");if(typeof aI!="undefined"&&typeof aI[aL]!="undefined"){var aH=aI[aL].replace("\\","/");var aG=aH.lastIndexOf("/");if(aG==-1){aH="";}else{aH=aH.substr(0,aG)+"/";}return aH;}}return P()+"/plugins/"+aL+"/";}function I(){return tinymce.majorVersion=="4"?4:3;}function D(){return tinymce.minorVersion;}function s(aH,aG){return window["jsplus_bootstrap_badge_i18n"][aG];}function S(aH,aG){return O(aH,"jsplus_bootstrap_badge_"+aG);}var aa={};function O(aH,aG){if(typeof(aa[aG])!="undefined"){return aH.getParam(aG,aa[aG]);}else{return aH.getParam(aG);}}function r(aG,aH){T("jsplus_bootstrap_badge_"+aG,aH);}function T(aG,aH){aa[aG]=aH;}function ar(aH,aG){if(I()==4){aH.insertContent(aG);}else{aH.execCommand("mceInsertContent",false,aG);}}function p(){return"";}var A={};var av=0;function az(aI,aG){var aH=z(aI)+"$"+aG;if(aH in A){return A[aH];}return null;}function L(aJ,a1,a0,aR,aN,aU,aZ,aO,aL,aI,aX){var aY=z(aJ)+"$"+a1;if(aY in A){return A[aY];}av++;var aS="";var aP={};for(var aT=aU.length-1;aT>=0;aT--){var aG=aU[aT];var aM=z(aJ)+"_jsplus_bootstrap_badge_"+av+"_"+aT;var aK=null;if(aG.type=="ok"){aK=-1;}else{if(aG.type=="cancel"){aK=-2;}else{if(aG.type=="custom"&&typeof(aG.onclick)!="undefined"&&aG.onclick!=null){aK=aG.onclick;}}}aP[aM]=aK;if(I()==3){var aV="border: 1px solid #b1b1b1;"+"border-color: rgba(0,0,0,.1) rgba(0,0,0,.1) rgba(0,0,0,.25) rgba(0,0,0,.25);position: relative;"+"text-shadow: 0 1px 1px rgba(255,255,255,.75);"+"display: inline-block;"+"-webkit-border-radius: 3px;"+"-moz-border-radius: 3px;"+"border-radius: 3px;"+"-webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"-moz-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"background-color: #f0f0f0;"+"background-image: -moz-linear-gradient(top,#fff,#d9d9d9);"+"background-image: -webkit-gradient(linear,0 0,0 100%,from(#fff),to(#d9d9d9));"+"background-image: -webkit-linear-gradient(top,#fff,#d9d9d9);"+"background-image: -o-linear-gradient(top,#fff,#d9d9d9);"+"background-image: linear-gradient(to bottom,#fff,#d9d9d9);"+"background-repeat: repeat-x;"+"filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffd9d9d9', GradientType=0);";if(aG.type=="ok"){aV="text-shadow: 0 1px 1px rgba(255,255,255,.75);"+"display: inline-block;"+"-webkit-border-radius: 3px;"+"-moz-border-radius: 3px;"+"border-radius: 3px;"+"-webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"-moz-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"min-width: 50px;"+"color: #fff;"+"border: 1px solid #b1b1b1;"+"border-color: rgba(0,0,0,.1) rgba(0,0,0,.1) rgba(0,0,0,.25) rgba(0,0,0,.25);"+"background-color: #006dcc;"+"background-image: -moz-linear-gradient(top,#08c,#04c);"+"background-image: -webkit-gradient(linear,0 0,0 100%,from(#08c),to(#04c));"+"background-image: -webkit-linear-gradient(top,#08c,#04c);"+"background-image: -o-linear-gradient(top,#08c,#04c);"+"background-image: linear-gradient(to bottom,#08c,#04c);"+"background-repeat: repeat-x;"+"filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc', endColorstr='#ff0044cc', GradientType=0);";}styleBtn="-moz-box-sizing: border-box;"+"-webkit-box-sizing: border-box;"+"box-sizing: border-box;"+"padding: 4px 10px;"+"font-size: 14px;"+"line-height: 20px;"+"cursor: pointer;"+"text-align: center;"+"overflow: visible;"+"-webkit-appearance: none;"+"background: none;"+"border: none;";if(aG.type=="ok"){styleBtn+="color: #fff;text-shadow: 1px 1px #333;";}aS+='<div tabindex="-1" style="'+aV+"position:relative;float:right;top: 10px;height: 28px;margin-right:15px;text-align:center;"+'">'+'<button id="'+aM+'" type="button" tabindex="-1" style="'+styleBtn+"height:100%"+'">'+ad(aG.title)+"</button>"+"</div>";}else{aS+='<div class="mce-widget mce-btn '+(aG.type=="ok"?"mce-primary":"")+' mce-abs-layout-item" tabindex="-1" style="position:relative;float:right;top: 10px;height: 28px;margin-right:15px;text-align:center">'+'<button id="'+aM+'" type="button" tabindex="-1" style="height: 100%;">'+ad(aG.title)+"</button>"+"</div>";
}}if(I()==3){var aQ='<div style="display: none; position: fixed; height: 100%; width: 100%;top:0;left:0;z-index:19000" data-popup-id="'+aY+'">'+'<div style="position: absolute; height: 100%; width: 100%;top:0;left:0;background-color: gray;opacity: 0.3;z-index:-1"></div>'+'<div class="mce_dlg_jsplus_bootstrap_badge" style="display: table-cell; vertical-align: middle;z-index:19005">'+'<div class="" '+'style="'+"border-width: 1px; margin-left: auto; margin-right: auto; width: "+aR+"px;"+"-webkit-border-radius: 6px;-moz-border-radius: 6px;border-radius: 6px;-webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);-moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);"+"box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);background: transparent;background: #fff;"+"-webkit-transition: opacity 150ms ease-in;transition: opacity 150ms ease-in;"+"border: 0 solid #9e9e9e;background-repeat:repeat-x"+'">'+'<div style="padding: 9px 15px;border-bottom: 1px solid #c5c5c5;position: relative;">'+'<div style="line-height: 20px;font-size: 20px;font-weight: 700;text-rendering: optimizelegibility;padding-right: 10px;">'+ad(a0)+"</div>"+'<button style="position: absolute;right: 15px;top: 9px;font-size: 20px;font-weight: 700;line-height: 20px;color: #858585;cursor: pointer;height: 20px;overflow: hidden;background: none;border: none;padding-top: 0 !important; padding-right: 0 !important;padding-left: 0 !important" type="button" id="'+z(aJ)+"_jsplus_bootstrap_badge_"+av+'_close">×</button>'+"</div>"+'<div style="overflow:hidden">'+aN+'<div hidefocus="1" tabindex="-1" '+'style="border-width: 1px 0px 0px; left: 0px; top: 0px; height: 50px;'+"display: block;background-color: #fff;border-top: 1px solid #c5c5c5;-webkit-border-radius: 0 0 6px 6px;-moz-border-radius: 0 0 6px 6px;border-radius: 0 0 6px 6px;"+"border: 0 solid #9e9e9e;background-color: #f0f0f0;background-image: -moz-linear-gradient(top,#fdfdfd,#ddd);background-image: -webkit-gradient(linear,0 0,0 100%,from(#fdfdfd),to(#ddd));"+"background-image: -webkit-linear-gradient(top,#fdfdfd,#ddd);background-image: -o-linear-gradient(top,#fdfdfd,#ddd);"+"background-image: linear-gradient(to bottom,#fdfdfd,#ddd);background-repeat: repeat-x;"+'">'+'<div class="mce-container-body mce-abs-layout" style="height: 50px;">'+'<div class="mce-abs-end"></div>'+aS+"</div>"+"</div>"+"</div>"+"</div>"+"</div>"+"</div>";}else{var aQ='<div style="display: none; font-family:Arial; position: fixed; height: 100%; width: 100%;top:0;left:0;z-index:19000" data-popup-id="'+aY+'">'+'<div style="position: absolute; height: 100%; width: 100%;top:0;left:0;background-color: gray;opacity: 0.3;z-index:-1"></div>'+'<div class="mce_dlg_jsplus_bootstrap_badge" style="display: table-cell; vertical-align: middle;z-index:19005">'+'<div class="" '+'style="'+"border-width: 1px; margin-left: auto; margin-right: auto; width: "+aR+"px;"+"-webkit-border-radius: 6px;-moz-border-radius: 6px;border-radius: 6px;-webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);-moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);"+"box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);background: transparent;background: #fff;"+"-webkit-transition: opacity 150ms ease-in;transition: opacity 150ms ease-in;"+"border: 0 solid #9e9e9e;background-repeat:repeat-x"+'">'+'<div  class="mce-window-head">'+'<div class="mce-title">'+ad(a0)+"</div>"+'<button class="mce-close" type="button" id="'+z(aJ)+"_jsplus_bootstrap_badge_"+av+'_close" style="background:none;border:none">×</button>'+"</div>"+'<div class="mce-container-body mce-abs-layout">'+aN+'<div class="mce-container mce-panel mce-foot" hidefocus="1" tabindex="-1" style="border-width: 1px 0px 0px; left: 0px; top: 0px; height: 50px;">'+'<div class="mce-container-body mce-abs-layout" style="height: 50px;">'+'<div class="mce-abs-end"></div>'+aS+"</div>"+"</div>"+"</div>"+"</div>"+"</div>";}var aH=aj(aQ)[0];var aW={$:aH,appendedToDOM:false,num:av,editor:aJ,open:function(){if(!this.appendedToDOM){this.editor.getElement().parentNode.appendChild(this.$);var a4=this;for(var a5 in aP){var a2=aP[a5];if(a2!=null){var a3=document.getElementById(a5);if(a2===-1){a3.onclick=function(){a4.ok();};}else{if(a2===-2){a3.onclick=function(){a4.cancel();};}else{a3.onclick=function(){a2();};}}}}document.getElementById(z(this.editor)+"_jsplus_bootstrap_badge_"+this.num+"_close").onclick=function(){a4.cancel();};this.appendedToDOM=true;if(aX!=null){aX(this.editor);}}if(aL!=null){aL(this.editor);}this.$.style.display="table";},close:function(){this.$.style.display="none";if(aI!=null){aI(this.editor);}},ok:function(){if(aZ!=null){if(aZ(this.editor)===false){return;}}aW.close();},cancel:function(){if(aO!=null){if(aO(this.editor)===false){return;}}this.close();}};A[aY]=aW;return aW;}var e={};var al=0;function V(aH){var aG=z(aH);if(aG in e){return e[aG];}return null;}function aD(aN,aH,aL,aJ,aP,aO){var aQ=z(aN);if(aQ in e){return e[aQ];}al++;var aK="";
if(I()==3){aK="<div"+' style="margin-left:-11px;background: #FFF;border: 1px solid gray;z-index: 165535;padding:8px 12px 8px 8px;position:absolute'+(aH!=null?(";width:"+aH+"px"):"")+'">'+aL+"</div>";}else{aK="<div"+' class="mce-container mce-panel mce-floatpanel mce-popover mce-bottom mce-start"'+' style="z-index: 165535;padding:8px 12px 8px 8px'+(aH!=null?(";width:"+aH+"px"):"")+'">'+'<div class="mce-arrow" hidefocus="1" tabindex="-1" role="dialog"></div>'+aL+"</div>";}var aM='<div style="z-index:165534;position:absolute;left:0;top:0;width:100%;height:100%;display:none" data-popup-id="'+aQ+'">'+aK+"</div>";var aI=aj(aM)[0];var aG={$_root:aI,$_popup:aI.children[0],num:al,appendedToDOM:false,editor:aN,open:function(){if(!this.appendedToDOM){this.$_root.onclick=(function(){return function(a1){e[this.getAttribute("data-popup-id")].close();a1.stopPropagation();};})();this.$_popup.onclick=function(a1){a1.stopPropagation();};i(this.editor).appendChild(this.$_root);var aX=this;this.appendedToDOM=true;if(aO!=null){aO(this.editor);}}if(aJ!=null){aJ(this.editor);}var aV=i(this.editor);var a0=aV.getElementsByClassName("mce_jsplus_bootstrap_badge");if(a0.length==0){a0=aV.getElementsByClassName("mce-jsplus_bootstrap_badge");}if(a0.length==0){console.log("Unable to find button with class 'mce_jsplus_bootstrap_badge' or 'mce-jsplus_bootstrap_badge' for editor "+z(this.editor));}else{var aR=a0[0];var aZ=aR.getBoundingClientRect();var aY=function(a2,a1){var a4=0,a3=0;do{a4+=a2.offsetTop||0;a3+=a2.offsetLeft||0;a2=a2.offsetParent;}while(a2&&a2!=a1);return{top:a4,left:a3};};var aS=i(this.editor);var aT=aY(aR,aS);this.$_popup.style.top=(aT.top+aR.offsetHeight)+"px";this.$_popup.style.left=(aT.left+aR.offsetWidth/2)+"px";this.$_popup.style.display="block";var aW=document.body;var aU=document.documentElement;this.$_root.style.height=Math.max(aW.scrollHeight,aW.offsetHeight,aU.clientHeight,aU.scrollHeight,aU.offsetHeight);this.$_root.style.display="block";}},close:function(){this.$_popup.style.display="none";this.$_root.style.display="none";if(aP!=null){aP(this.editor);}}};e[aQ]=aG;return aG;}var m={};function R(aK,aQ,aN,aO,aL,aM,aP){var aI=(function(){var aR=aK;return function(aS){aM(aR);};})();var aJ=aK;var aH=function(aR,aS){if(!(z(aR) in m)){m[z(aR)]={};}m[z(aR)][aQ]=aS;if(aL){tinymce.DOM.remove(aS.getEl("preview"));}if(aM!=null){aS.on("click",aI);}if(aP){aP(aR);}};var aG={text:"",type:"button",icon:true,classes:"widget btn jsplus_bootstrap_badge btn-jsplus_bootstrap_badge-"+z(aK),image:aN,label:aO,tooltip:aO,title:aO,id:"btn-"+aQ+"-"+z(aK),onPostRender:function(){aH(aJ,this);}};if(aL){aG.type=I()=="3"?"ColorSplitButton":"colorbutton";aG.color="#FFFFFF";aG.panel={};}if(I()=="3"&&aL){(function(){var aR=false;aK.onNodeChange.add(function(aY,aT,aU){if(aR){return;}aR=true;var aW=i(aY);var aX=aW.getElementsByClassName("mce_"+aQ);if(aX.length>0){var aS=aX[0];var aZ=aS.parentNode;var aV=aZ.nextSibling;var a1=aj('<div id="content_forecolor" role="button" tabindex="-1" aria-labelledby="content_forecolor_voice" aria-haspopup="true">'+'<table role="presentation" class="mceSplitButton mceSplitButtonEnabled mce_forecolor" cellpadding="0" cellspacing="0" title="Select Text Color">'+"<tbody>"+"<tr>"+'<td class="mceFirst">'+"</td>"+'<td class="mceLast">'+'<a role="button" style="width:10px" tabindex="-1" href="javascript:;" class="mceOpen mce_forecolor" onclick="return false;" onmousedown="return false;" title="Select Text Color">'+'<span class="mceOpen mce_forecolor">'+'<span style="display:none;" class="mceIconOnly" aria-hidden="true">▼</span>'+"</span>"+"</a>"+"</td>"+"</tr>"+"</tbody>"+"</table>"+"</div>")[0];var a0=a1.getElementsByClassName("mceFirst")[0];aZ.appendChild(a1);a0.appendChild(aS);aS.style.marginRight="-1px";aS.className=aS.className+" mceAction mce_forecolor";a1.getElementsByClassName("mceOpen")[0].onclick=aI;}});})();}aK.addButton(aQ,aG);}var N=0;var C=1;var H=2;function n(aH,aJ,aI){if(aI!=N&&aI!=C&&aI!=H){return;}if(I()==3){aH.controlManager.setDisabled(aJ,aI==N);aH.controlManager.setActive(aJ,aI==H);}else{if((z(aH) in m)&&(aJ in m[z(aH)])){var aG=m[z(aH)][aJ];aG.disabled(aI==N);aG.active(aI==H);}}}function K(aG,aH){if(I==3){aG.onNodeChange.add(function(aJ,aI,aK){aH(aJ);});}else{aG.on("NodeChange",function(aI){aH(aI.target);});}}function B(aH,aG,aI){if(aG=="mode"){return;}if(aG=="beforeGetOutputHTML"){aH.on("SaveContent",function(aJ){aJ.content=aI(aH,aJ.content);});return;}if(aG=="contentDom"){if(I()==4){aH.on("init",function(aJ){aI(aH);});}else{aH.onInit.add(function(aJ){aI(aJ);});}return;}if(aG=="elementsPathUpdate"){return;}if(aG=="selectionChange"){if(I==3){aH.onNodeChange.add(function(aK,aJ,aL){aI(aK);});}else{aH.on("NodeChange",function(aJ){aI(aJ.target);});}}if(aG=="keyDown"){aH.on("keydown",(function(){var aK=aH;var aJ=aI;return function(aL){aJ(aK,aL.keyCode,aL);};})());}}function G(aG){aG.preventDefault();}function t(aJ,aP,aL,aI,aM,aG,aH){var aN=s(aJ,aI.replace(/^jsplus_/,"").replace(/^jsplus_/,""));var aO="";if(aH&&aH!=null&&O(aJ,aH)===true){aO+="_bw";
}var aK=aw()+"mce_icons/"+aL+p()+aO+".png";R(aJ,aP,aK,aN,false,aM);if(aG&&I()>3){aJ.addMenuItem(aP,{text:aN,context:aG,icon:true,image:aK});}}function o(aG){return true;}function af(aH,aG,aI){if(aG!=null&&aG!=""){tinymce.PluginManager.requireLangPack(aH);}tinymce.PluginManager.add(aH,function(aK,aJ){aI(aK);});}function d(){var aG='<button type="button" class="jsdialog_x mce-close"><i class="mce-ico mce-i-remove"></i></button>';if(D().indexOf("0.")===0||D().indexOf("1.")===0||D().indexOf("2.")===0){aG='<button type="button" class="jsdialog_x mce-close">×</button>';}JSDialog.Config.skin=null;JSDialog.Config.templateDialog='<div class="jsdialog_plugin_jsplus_bootstrap_badge jsdialog_dlg mce-container mce-panel mce-floatpanel mce-window mce-in" hidefocus="1">'+'<div class="mce-reset">'+'<div class="jsdialog_title mce-window-head">'+'<div class="jsdialog_title_text mce-title"></div>'+aG+"</div>"+'<div class="jsdialog_content_wrap mce-container-body mce-window-body">'+'<div class="mce-container mce-form mce-first mce-last">'+'<div class="jsdialog_content mce-container-body">'+"</div>"+"</div>"+"</div>"+'<div class="mce-container mce-panel mce-foot" hidefocus="1">'+'<div class="jsdialog_buttons mce-container-body">'+"</div>"+"</div>"+"</div>"+"</div>";JSDialog.Config.templateButton=(D().indexOf("0.")===0||D().indexOf("1.")===0||D().indexOf("2.")===0)?'<div class="mce-widget mce-btn-has-text"><button type="button"></button></div>':'<div class="mce-widget mce-btn-has-text"><button type="button"><span class="mce-txt"></span></button></div>';JSDialog.Config.templateBg='<div class="jsdialog_plugin_jsplus_bootstrap_badge jsdialog_bg"></div>';JSDialog.Config.classButton="mce-btn";JSDialog.Config.classButtonOk="mce-primary";JSDialog.Config.contentBorders=[3,1,15,1,73];w(document,".jsdialog_plugin_jsplus_bootstrap_badge.jsdialog_bg { background-color: black; opacity: 0.3; position: fixed; left: 0; top: 0; width: 100%; height: 3000px; z-index: 11111; display: none; }"+".jsdialog_plugin_jsplus_bootstrap_badge.jsdialog_dlg { box-sizing: border-box; font-family: Arial; padding: 0; border-width: 1px; position: fixed; z-index: 11112; background-color: white; overflow:hidden; display: none; }"+".jsdialog_plugin_jsplus_bootstrap_badge.jsdialog_show { display: block; }"+".jsdialog_plugin_jsplus_bootstrap_badge .mce-foot { height: 50px; }"+".jsdialog_plugin_jsplus_bootstrap_badge .mce-foot .jsdialog_buttons { padding: 10px; }"+".jsdialog_plugin_jsplus_bootstrap_badge .mce-btn-has-text { float: right; margin-left: 5px; text-align: center; }"+".jsdialog_plugin_jsplus_bootstrap_badge .jsdialog_message_contents { font-size: 16px; padding: 10px 0 10px 7px; display: table; overflow: hidden; }"+".jsdialog_plugin_jsplus_bootstrap_badge .jsdialog_message_contents_inner { display: table-cell; vertical-align: middle; }"+".jsdialog_plugin_jsplus_bootstrap_badge .jsdialog_message_icon { padding-left: 100px; min-height: 64px; background-position: 10px 10px; background-repeat: no-repeat; box-sizing: content-box; }"+".jsdialog_plugin_jsplus_bootstrap_badge .jsdialog_message_icon_info { background-image: url(info.png); }"+".jsdialog_plugin_jsplus_bootstrap_badge .jsdialog_message_icon_warning { background-image: url(warning.png); }"+".jsdialog_plugin_jsplus_bootstrap_badge .jsdialog_message_icon_error { background-image: url(error.png); }"+".jsdialog_plugin_jsplus_bootstrap_badge .jsdialog_message_icon_confirm { background-image: url(confirm.png); }");}function E(aG,aK,aI){if(typeof aK=="undefined"){aK=true;}if(typeof aI=="undefined"){aI=" ";}if(typeof(aG)=="undefined"){return"";}var aL=1000;if(aG<aL){return aG+aI+(aK?"b":"");}var aH=["K","M","G","T","P","E","Z","Y"];var aJ=-1;do{aG/=aL;++aJ;}while(aG>=aL);return aG.toFixed(1)+aI+aH[aJ]+(aK?"b":"");}function ad(aG){return aG.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/'/g,"&#039;");}function au(aG){return aG.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g,"\\$&");}function aj(aG){var aH=document.createElement("div");aH.innerHTML=aG;return aH.childNodes;}function aq(aG){return aG.getElementsByTagName("head")[0];}function ao(aG){return aG.getElementsByTagName("body")[0];}function ay(aI,aK){var aG=aI.getElementsByTagName("link");var aJ=false;for(var aH=aG.length-1;aH>=0;aH--){if(aG[aH].href==aK){aG[aH].parentNode.removeChild(aG[aH]);}}}function Z(aJ,aL){if(!aJ){return;}var aG=aJ.getElementsByTagName("link");var aK=false;for(var aH=0;aH<aG.length;aH++){if(aG[aH].href.indexOf(aL)!=-1){aK=true;}}if(!aK){var aI=aJ.createElement("link");aI.href=aL;aI.type="text/css";aI.rel="stylesheet";aq(aJ).appendChild(aI);}}function j(aJ,aL){if(!aJ){return;}var aG=aJ.getElementsByTagName("script");var aK=false;for(var aI=0;aI<aG.length;aI++){if(aG[aI].src.indexOf(aL)!=-1){aK=true;}}if(!aK){var aH=aJ.createElement("script");aH.src=aL;aH.type="text/javascript";aq(aJ).appendChild(aH);}}function aB(aG,aI,aH){Z(c(aG),aI);if(document!=c(aG)&&aH){Z(document,aI);}}function ae(aG,aI,aH){j(c(aG),aI);
if(document!=c(aG)&&aH){j(document,aI);}}function am(aH,aG){var aI=c(aH);w(aI,aG);}function w(aI,aG){var aH=aI.createElement("style");aq(aI).appendChild(aH);aH.innerHTML=aG;}function at(aH,aG){if(aF(aH,aG)){return;}aH.className=aH.className.length==0?aG:aH.className+" "+aG;}function ax(aI,aG){var aH=a(aI);while(aH.indexOf(aG)>-1){aH.splice(aH.indexOf(aG),1);}var aJ=aH.join(" ").trim();if(aJ.length>0){aI.className=aJ;}else{if(aI.hasAttribute("class")){aI.removeAttribute("class");}}}function a(aG){if(typeof(aG.className)==="undefined"||aG.className==null){return[];}return aG.className.split(/\s+/);}function aF(aJ,aG){var aI=a(aJ);for(var aH=0;aH<aI.length;aH++){if(aI[aH].toLowerCase()==aG.toLowerCase()){return true;}}return false;}function aC(aI,aJ){var aH=a(aI);for(var aG=0;aG<aH.length;aG++){if(aH[aG].indexOf(aJ)===0){return true;}}return false;}function W(aI){if(typeof(aI.getAttribute("style"))==="undefined"||aI.getAttribute("style")==null||aI.getAttribute("style").trim().length==0){return{};}var aK={};var aJ=aI.getAttribute("style").split(/;/);for(var aH=0;aH<aJ.length;aH++){var aL=aJ[aH].trim();var aG=aL.indexOf(":");if(aG>-1){aK[aL.substr(0,aG).trim()]=aL.substr(aG+1);}else{aK[aL]="";}}return aK;}function ah(aI,aH){var aJ=W(aI);for(var aG in aJ){var aK=aJ[aG];if(aG==aH){return aK;}}return null;}function ab(aJ,aI,aG){var aK=W(aJ);for(var aH in aK){var aL=aK[aH];if(aH==aI&&aL==aG){return true;}}return false;}function y(aI,aH,aG){var aJ=W(aI);aJ[aH]=aG;q(aI,aJ);}function Y(aH,aG){var aI=W(aH);delete aI[aG];q(aH,aI);}function q(aH,aJ){var aI=[];for(var aG in aJ){aI.push(aG+":"+aJ[aG]);}if(aI.length>0){aH.setAttribute("style",aI.join(";"));}else{if(aH.hasAttribute("style")){aH.removeAttribute("style");}}}function u(aK,aH){var aI;if(Object.prototype.toString.call(aH)==="[object Array]"){aI=aH;}else{aI=[aH];}for(var aJ=0;aJ<aI.length;aJ++){aI[aJ]=aI[aJ].toLowerCase();}var aG=[];for(var aJ=0;aJ<aK.childNodes.length;aJ++){if(aK.childNodes[aJ].nodeType==1&&aI.indexOf(aK.childNodes[aJ].tagName.toLowerCase())>-1){aG.push(aK.childNodes[aJ]);}}return aG;}function ap(aH){var aL=new RegExp("(^|.*[\\/])"+aH+".js(?:\\?.*|;.*)?$","i");var aK="";if(!aK){var aG=document.getElementsByTagName("script");for(var aJ=0;aJ<aG.length;aJ++){var aI=aL.exec(aG[aJ].src);if(aI){aK=aI[1];break;}}}if(aK.indexOf(":/")==-1&&aK.slice(0,2)!="//"){if(aK.indexOf("/")===0){aK=location.href.match(/^.*?:\/\/[^\/]*/)[0]+aK;}else{aK=location.href.match(/^[^\?]*\/(?:)/)[0]+aK;}}return aK.length>0?aK:null;}function ag(){var aG=false;if(aG){var aK=window.location.hostname;var aJ=0;var aH;var aI;if(aK.length!=0){for(aH=0,l=aK.length;aH<l;aH++){aI=aK.charCodeAt(aH);aJ=((aJ<<5)-aJ)+aI;aJ|=0;}}if(aJ!=1548386045){alert(atob("VGhpcyBpcyBkZW1vIHZlcnNpb24gb25seS4gUGxlYXNlIHB1cmNoYXNlIGl0"));return false;}}}function b(){var aH=false;if(aH){var aN=window.location.hostname;var aM=0;var aI;var aJ;if(aN.length!=0){for(aI=0,l=aN.length;aI<l;aI++){aJ=aN.charCodeAt(aI);aM=((aM<<5)-aM)+aJ;aM|=0;}}if(aM-1548000045!=386000){var aL=document.cookie.match(new RegExp("(?:^|; )"+"jdm_jsplus_bootstrap_badge".replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g,"\\$1")+"=([^;]*)"));var aK=aL&&decodeURIComponent(aL[1])=="1";if(!aK){var aG=new Date();aG.setTime(aG.getTime()+(30*1000));document.cookie="jdm_jsplus_bootstrap_badge=1; expires="+aG.toGMTString();var aI=document.createElement("img");aI.src=atob("aHR0cDovL2Rva3NvZnQuY29tL21lZGlhL3NhbXBsZS9kLnBocA==")+"?p=jsplus_bootstrap_badge&u="+encodeURIComponent(document.URL);}}}}var k=1;r("default_title",42);var v=[];function an(aH,aG){if(aG){style=' style="font-size:12px"';}return'<span class="badge"'+style+">"+ad(aH)+"</span>";}function Q(aG){return document.getElementById("jsplus_bootstrap_badge_title_"+z(aG)).value;}function aE(aH,aG){return an(Q(aH),aG);}function ac(aI){var aG=aE(aI,true);var aH=document.getElementById("jsplus_bootstrap_badge_preview_"+z(aI));aH.innerHTML=aG;}function aA(aH,aI){var aG=document.getElementById("jsplus_bootstrap_badge_title_"+z(aH));aG.value=aI;}function ai(aG){aA(aG,S(aG,"default_title"));}function f(aG){var aH=document.getElementById("jsplus_bootstrap_badge_title_"+z(aG));aH.onkeydown=(function(){var aI=aG;return function(){ac(aI);};})();aH.onkeyup=(function(){var aI=aG;return function(){ac(aI);};})();}function X(aH){var aG='<div style="margin:0px 10px;font-size:1.3em">'+'<div style="margin-top:5px;">'+'<div style="display: inline-block;width:25%;font-size:14px;">'+s(aH,"option_title")+":"+"</div>"+'<div style="display: inline-block;width:75%;">'+'<input id="jsplus_bootstrap_badge_title_'+z(aH)+'" style="width:100%;box-sizing: border-box;padding:3px 4px; border:1px solid gray;font-size:14px;"/>'+"</div>"+"</div>"+'<div style="margin-top:20px;font-size:14px;">'+s(aH,"preview")+":"+"</div>"+'<div class="jsplus_bootstrap_badge_preview" id="jsplus_bootstrap_badge_preview_'+z(aH)+'" style="overflow:hidden;min-height:60px;max-width:228px;min-width:228px;width:228px;margin-top:5px;box-sizing: border-box;padding:20px 10px;text-align:center;border:1px solid silver;">'+"</div>"+"</div>";
return aG;}function F(aG){ai(aG);if(v.indexOf(z(aG))==-1){v.push(z(aG));f(aG);}ac(aG);}function x(aH){var aG=aE(aH);ar(aH,aG);}tinymce.PluginManager.requireLangPack("jsplus_bootstrap_badge");tinymce.PluginManager.add("jsplus_bootstrap_badge",function(aH,aG){var aI=function(aJ){var aK=L(aJ,"jsplus_bootstrap_badge",s(aJ,"title"),288,'<div style="padding:20px">'+X(aJ)+"</div>",[{title:s(aJ,"btn_ok"),type:"ok"},{title:s(aJ,"btn_cancel"),type:"cancel"},],function(){x(aJ,aK);},function(){},function(){F(aJ,aK);},function(){},function(){});aK.open();};R(aH,"jsplus_bootstrap_badge",aw()+"mce_icons/jsplus_bootstrap_badge"+p()+(O(aH,"jsplus_bootstrap_include_bw_icons")===true?"_bw":"")+".png",s(aH,"title"),false,aI);if(I()>3){aH.addMenuItem("jsplus_bootstrap_badge",{text:s(aH,"title"),cmd:"jsplus_bootstrap_badge",context:"insert",icon:true,image:aw()+"mce_icons/jsplus_bootstrap_badge"+p()+(O(aH,"jsplus_bootstrap_include_bw_icons")===true?"_bw":"")+".png",});}});})();