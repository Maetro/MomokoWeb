(function(){function aB(){return"tinymce";}function k(aV){return T()!="3"&&aV.inline;}function H(aV){return aV.id.replace(/\[/,"_").replace(/\]/,"_");}function o(aW){if(T()=="3"||!k(aW)){return aW.getContainer();}var aV=window.document.getElementById(aW.theme.panel._id);return aV;}function e(aV){return aV.getDoc();}function U(aV){return aV.getContent();}function X(aW,aV){aW.setContent(aV);}function ai(aV){if(tinymce.activeEditor==null||tinymce.activeEditor.selection==null){return null;}return tinymce.activeEditor.selection.getNode();}function ab(){return tinymce.baseURL;}function aN(){return m("jsplus_bootstrap_table_row_move_up");}function m(a0){for(var aY=0;aY<tinymce.editors.length;aY++){var aZ=tinymce.editors[aY];var aX=aa(aZ,"external_plugins");if(typeof aX!="undefined"&&typeof aX[a0]!="undefined"){var aW=aX[a0].replace("\\","/");var aV=aW.lastIndexOf("/");if(aV==-1){aW="";}else{aW=aW.substr(0,aV)+"/";}return aW;}}return ab()+"/plugins/"+a0+"/";}function T(){return tinymce.majorVersion=="4"?4:3;}function O(){return tinymce.minorVersion;}function B(aW,aV){return window["jsplus_bootstrap_table_row_move_up_i18n"][aV];}function ad(aW,aV){return aa(aW,"jsplus_bootstrap_table_row_move_up_"+aV);}var ao={};function aa(aW,aV){if(typeof(ao[aV])!="undefined"){return aW.getParam(aV,ao[aV]);}else{return aW.getParam(aV);}}function A(aV,aW){ah("jsplus_bootstrap_table_row_move_up_"+aV,aW);}function ah(aV,aW){ao[aV]=aW;}function aJ(aW,aV){if(T()==4){aW.insertContent(aV);}else{aW.execCommand("mceInsertContent",false,aV);}}function w(){return"";}var I={};var aM=0;function aQ(aX,aV){var aW=H(aX)+"$"+aV;if(aW in I){return I[aW];}return null;}function W(aY,bg,bf,a6,a2,a9,be,a3,a0,aX,bc){var bd=H(aY)+"$"+bg;if(bd in I){return I[bd];}aM++;var a7="";var a4={};for(var a8=a9.length-1;a8>=0;a8--){var aV=a9[a8];var a1=H(aY)+"_jsplus_bootstrap_table_row_move_up_"+aM+"_"+a8;var aZ=null;if(aV.type=="ok"){aZ=-1;}else{if(aV.type=="cancel"){aZ=-2;}else{if(aV.type=="custom"&&typeof(aV.onclick)!="undefined"&&aV.onclick!=null){aZ=aV.onclick;}}}a4[a1]=aZ;if(T()==3){var ba="border: 1px solid #b1b1b1;"+"border-color: rgba(0,0,0,.1) rgba(0,0,0,.1) rgba(0,0,0,.25) rgba(0,0,0,.25);position: relative;"+"text-shadow: 0 1px 1px rgba(255,255,255,.75);"+"display: inline-block;"+"-webkit-border-radius: 3px;"+"-moz-border-radius: 3px;"+"border-radius: 3px;"+"-webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"-moz-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"background-color: #f0f0f0;"+"background-image: -moz-linear-gradient(top,#fff,#d9d9d9);"+"background-image: -webkit-gradient(linear,0 0,0 100%,from(#fff),to(#d9d9d9));"+"background-image: -webkit-linear-gradient(top,#fff,#d9d9d9);"+"background-image: -o-linear-gradient(top,#fff,#d9d9d9);"+"background-image: linear-gradient(to bottom,#fff,#d9d9d9);"+"background-repeat: repeat-x;"+"filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffd9d9d9', GradientType=0);";if(aV.type=="ok"){ba="text-shadow: 0 1px 1px rgba(255,255,255,.75);"+"display: inline-block;"+"-webkit-border-radius: 3px;"+"-moz-border-radius: 3px;"+"border-radius: 3px;"+"-webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"-moz-box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"box-shadow: inset 0 1px 0 rgba(255,255,255,.2),0 1px 2px rgba(0,0,0,.05);"+"min-width: 50px;"+"color: #fff;"+"border: 1px solid #b1b1b1;"+"border-color: rgba(0,0,0,.1) rgba(0,0,0,.1) rgba(0,0,0,.25) rgba(0,0,0,.25);"+"background-color: #006dcc;"+"background-image: -moz-linear-gradient(top,#08c,#04c);"+"background-image: -webkit-gradient(linear,0 0,0 100%,from(#08c),to(#04c));"+"background-image: -webkit-linear-gradient(top,#08c,#04c);"+"background-image: -o-linear-gradient(top,#08c,#04c);"+"background-image: linear-gradient(to bottom,#08c,#04c);"+"background-repeat: repeat-x;"+"filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc', endColorstr='#ff0044cc', GradientType=0);";}styleBtn="-moz-box-sizing: border-box;"+"-webkit-box-sizing: border-box;"+"box-sizing: border-box;"+"padding: 4px 10px;"+"font-size: 14px;"+"line-height: 20px;"+"cursor: pointer;"+"text-align: center;"+"overflow: visible;"+"-webkit-appearance: none;"+"background: none;"+"border: none;";if(aV.type=="ok"){styleBtn+="color: #fff;text-shadow: 1px 1px #333;";}a7+='<div tabindex="-1" style="'+ba+"position:relative;float:right;top: 10px;height: 28px;margin-right:15px;text-align:center;"+'">'+'<button id="'+a1+'" type="button" tabindex="-1" style="'+styleBtn+"height:100%"+'">'+aq(aV.title)+"</button>"+"</div>";}else{a7+='<div class="mce-widget mce-btn '+(aV.type=="ok"?"mce-primary":"")+' mce-abs-layout-item" tabindex="-1" style="position:relative;float:right;top: 10px;height: 28px;margin-right:15px;text-align:center">'+'<button id="'+a1+'" type="button" tabindex="-1" style="height: 100%;">'+aq(aV.title)+"</button>"+"</div>";
}}if(T()==3){var a5='<div style="display: none; position: fixed; height: 100%; width: 100%;top:0;left:0;z-index:19000" data-popup-id="'+bd+'">'+'<div style="position: absolute; height: 100%; width: 100%;top:0;left:0;background-color: gray;opacity: 0.3;z-index:-1"></div>'+'<div class="mce_dlg_jsplus_bootstrap_table_row_move_up" style="display: table-cell; vertical-align: middle;z-index:19005">'+'<div class="" '+'style="'+"border-width: 1px; margin-left: auto; margin-right: auto; width: "+a6+"px;"+"-webkit-border-radius: 6px;-moz-border-radius: 6px;border-radius: 6px;-webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);-moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);"+"box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);background: transparent;background: #fff;"+"-webkit-transition: opacity 150ms ease-in;transition: opacity 150ms ease-in;"+"border: 0 solid #9e9e9e;background-repeat:repeat-x"+'">'+'<div style="padding: 9px 15px;border-bottom: 1px solid #c5c5c5;position: relative;">'+'<div style="line-height: 20px;font-size: 20px;font-weight: 700;text-rendering: optimizelegibility;padding-right: 10px;">'+aq(bf)+"</div>"+'<button style="position: absolute;right: 15px;top: 9px;font-size: 20px;font-weight: 700;line-height: 20px;color: #858585;cursor: pointer;height: 20px;overflow: hidden;background: none;border: none;padding-top: 0 !important; padding-right: 0 !important;padding-left: 0 !important" type="button" id="'+H(aY)+"_jsplus_bootstrap_table_row_move_up_"+aM+'_close">×</button>'+"</div>"+'<div style="overflow:hidden">'+a2+'<div hidefocus="1" tabindex="-1" '+'style="border-width: 1px 0px 0px; left: 0px; top: 0px; height: 50px;'+"display: block;background-color: #fff;border-top: 1px solid #c5c5c5;-webkit-border-radius: 0 0 6px 6px;-moz-border-radius: 0 0 6px 6px;border-radius: 0 0 6px 6px;"+"border: 0 solid #9e9e9e;background-color: #f0f0f0;background-image: -moz-linear-gradient(top,#fdfdfd,#ddd);background-image: -webkit-gradient(linear,0 0,0 100%,from(#fdfdfd),to(#ddd));"+"background-image: -webkit-linear-gradient(top,#fdfdfd,#ddd);background-image: -o-linear-gradient(top,#fdfdfd,#ddd);"+"background-image: linear-gradient(to bottom,#fdfdfd,#ddd);background-repeat: repeat-x;"+'">'+'<div class="mce-container-body mce-abs-layout" style="height: 50px;">'+'<div class="mce-abs-end"></div>'+a7+"</div>"+"</div>"+"</div>"+"</div>"+"</div>"+"</div>";}else{var a5='<div style="display: none; font-family:Arial; position: fixed; height: 100%; width: 100%;top:0;left:0;z-index:19000" data-popup-id="'+bd+'">'+'<div style="position: absolute; height: 100%; width: 100%;top:0;left:0;background-color: gray;opacity: 0.3;z-index:-1"></div>'+'<div class="mce_dlg_jsplus_bootstrap_table_row_move_up" style="display: table-cell; vertical-align: middle;z-index:19005">'+'<div class="" '+'style="'+"border-width: 1px; margin-left: auto; margin-right: auto; width: "+a6+"px;"+"-webkit-border-radius: 6px;-moz-border-radius: 6px;border-radius: 6px;-webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);-moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);"+"box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);background: transparent;background: #fff;"+"-webkit-transition: opacity 150ms ease-in;transition: opacity 150ms ease-in;"+"border: 0 solid #9e9e9e;background-repeat:repeat-x"+'">'+'<div  class="mce-window-head">'+'<div class="mce-title">'+aq(bf)+"</div>"+'<button class="mce-close" type="button" id="'+H(aY)+"_jsplus_bootstrap_table_row_move_up_"+aM+'_close" style="background:none;border:none">×</button>'+"</div>"+'<div class="mce-container-body mce-abs-layout">'+a2+'<div class="mce-container mce-panel mce-foot" hidefocus="1" tabindex="-1" style="border-width: 1px 0px 0px; left: 0px; top: 0px; height: 50px;">'+'<div class="mce-container-body mce-abs-layout" style="height: 50px;">'+'<div class="mce-abs-end"></div>'+a7+"</div>"+"</div>"+"</div>"+"</div>"+"</div>";}var aW=aA(a5)[0];var bb={$:aW,appendedToDOM:false,num:aM,editor:aY,open:function(){if(!this.appendedToDOM){this.editor.getElement().parentNode.appendChild(this.$);var bj=this;for(var bk in a4){var bh=a4[bk];if(bh!=null){var bi=document.getElementById(bk);if(bh===-1){bi.onclick=function(){bj.ok();};}else{if(bh===-2){bi.onclick=function(){bj.cancel();};}else{bi.onclick=function(){bh();};}}}}document.getElementById(H(this.editor)+"_jsplus_bootstrap_table_row_move_up_"+this.num+"_close").onclick=function(){bj.cancel();};this.appendedToDOM=true;if(bc!=null){bc(this.editor);}}if(a0!=null){a0(this.editor);}this.$.style.display="table";},close:function(){this.$.style.display="none";if(aX!=null){aX(this.editor);}},ok:function(){if(be!=null){if(be(this.editor)===false){return;}}bb.close();},cancel:function(){if(a3!=null){if(a3(this.editor)===false){return;}}this.close();}};I[bd]=bb;return bb;}var g={};var aC=0;function aj(aW){var aV=H(aW);if(aV in g){return g[aV];}return null;}function aT(a2,aW,a0,aY,a4,a3){var a5=H(a2);
if(a5 in g){return g[a5];}aC++;var aZ="";if(T()==3){aZ="<div"+' style="margin-left:-11px;background: #FFF;border: 1px solid gray;z-index: 165535;padding:8px 12px 8px 8px;position:absolute'+(aW!=null?(";width:"+aW+"px"):"")+'">'+a0+"</div>";}else{aZ="<div"+' class="mce-container mce-panel mce-floatpanel mce-popover mce-bottom mce-start"'+' style="z-index: 165535;padding:8px 12px 8px 8px'+(aW!=null?(";width:"+aW+"px"):"")+'">'+'<div class="mce-arrow" hidefocus="1" tabindex="-1" role="dialog"></div>'+a0+"</div>";}var a1='<div style="z-index:165534;position:absolute;left:0;top:0;width:100%;height:100%;display:none" data-popup-id="'+a5+'">'+aZ+"</div>";var aX=aA(a1)[0];var aV={$_root:aX,$_popup:aX.children[0],num:aC,appendedToDOM:false,editor:a2,open:function(){if(!this.appendedToDOM){this.$_root.onclick=(function(){return function(bg){g[this.getAttribute("data-popup-id")].close();bg.stopPropagation();};})();this.$_popup.onclick=function(bg){bg.stopPropagation();};o(this.editor).appendChild(this.$_root);var bc=this;this.appendedToDOM=true;if(a3!=null){a3(this.editor);}}if(aY!=null){aY(this.editor);}var ba=o(this.editor);var bf=ba.getElementsByClassName("mce_jsplus_bootstrap_table_row_move_up");if(bf.length==0){bf=ba.getElementsByClassName("mce-jsplus_bootstrap_table_row_move_up");}if(bf.length==0){console.log("Unable to find button with class 'mce_jsplus_bootstrap_table_row_move_up' or 'mce-jsplus_bootstrap_table_row_move_up' for editor "+H(this.editor));}else{var a6=bf[0];var be=a6.getBoundingClientRect();var bd=function(bh,bg){var bj=0,bi=0;do{bj+=bh.offsetTop||0;bi+=bh.offsetLeft||0;bh=bh.offsetParent;}while(bh&&bh!=bg);return{top:bj,left:bi};};var a7=o(this.editor);var a8=bd(a6,a7);this.$_popup.style.top=(a8.top+a6.offsetHeight)+"px";this.$_popup.style.left=(a8.left+a6.offsetWidth/2)+"px";this.$_popup.style.display="block";var bb=document.body;var a9=document.documentElement;this.$_root.style.height=Math.max(bb.scrollHeight,bb.offsetHeight,a9.clientHeight,a9.scrollHeight,a9.offsetHeight);this.$_root.style.display="block";}},close:function(){this.$_popup.style.display="none";this.$_root.style.display="none";if(a4!=null){a4(this.editor);}}};g[a5]=aV;return aV;}var t={};function ac(aZ,a5,a2,a3,a0,a1,a4){var aX=(function(){var a6=aZ;return function(a7){a1(a6);};})();var aY=aZ;var aW=function(a6,a7){if(!(H(a6) in t)){t[H(a6)]={};}t[H(a6)][a5]=a7;if(a0){tinymce.DOM.remove(a7.getEl("preview"));}if(a1!=null){a7.on("click",aX);}if(a4){a4(a6);}};var aV={text:"",type:"button",icon:true,classes:"widget btn jsplus_bootstrap_table_row_move_up btn-jsplus_bootstrap_table_row_move_up-"+H(aZ),image:a2,label:a3,tooltip:a3,title:a3,id:"btn-"+a5+"-"+H(aZ),onPostRender:function(){aW(aY,this);}};if(a0){aV.type=T()=="3"?"ColorSplitButton":"colorbutton";aV.color="#FFFFFF";aV.panel={};}if(T()=="3"&&a0){(function(){var a6=false;aZ.onNodeChange.add(function(bd,a8,a9){if(a6){return;}a6=true;var bb=o(bd);var bc=bb.getElementsByClassName("mce_"+a5);if(bc.length>0){var a7=bc[0];var be=a7.parentNode;var ba=be.nextSibling;var bg=aA('<div id="content_forecolor" role="button" tabindex="-1" aria-labelledby="content_forecolor_voice" aria-haspopup="true">'+'<table role="presentation" class="mceSplitButton mceSplitButtonEnabled mce_forecolor" cellpadding="0" cellspacing="0" title="Select Text Color">'+"<tbody>"+"<tr>"+'<td class="mceFirst">'+"</td>"+'<td class="mceLast">'+'<a role="button" style="width:10px" tabindex="-1" href="javascript:;" class="mceOpen mce_forecolor" onclick="return false;" onmousedown="return false;" title="Select Text Color">'+'<span class="mceOpen mce_forecolor">'+'<span style="display:none;" class="mceIconOnly" aria-hidden="true">▼</span>'+"</span>"+"</a>"+"</td>"+"</tr>"+"</tbody>"+"</table>"+"</div>")[0];var bf=bg.getElementsByClassName("mceFirst")[0];be.appendChild(bg);bf.appendChild(a7);a7.style.marginRight="-1px";a7.className=a7.className+" mceAction mce_forecolor";bg.getElementsByClassName("mceOpen")[0].onclick=aX;}});})();}aZ.addButton(a5,aV);}var Z=0;var K=1;var S=2;function u(aW,aY,aX){if(aX!=Z&&aX!=K&&aX!=S){return;}if(T()==3){aW.controlManager.setDisabled(aY,aX==Z);aW.controlManager.setActive(aY,aX==S);}else{if((H(aW) in t)&&(aY in t[H(aW)])){var aV=t[H(aW)][aY];aV.disabled(aX==Z);aV.active(aX==S);}}}function V(aV,aW){if(T==3){aV.onNodeChange.add(function(aY,aX,aZ){aW(aY);});}else{aV.on("NodeChange",function(aX){aW(aX.target);});}}function J(aW,aV,aX){if(aV=="mode"){return;}if(aV=="beforeGetOutputHTML"){aW.on("SaveContent",function(aY){aY.content=aX(aW,aY.content);});return;}if(aV=="contentDom"){if(T()==4){aW.on("init",function(aY){aX(aW);});}else{aW.onInit.add(function(aY){aX(aY);});}return;}if(aV=="elementsPathUpdate"){return;}if(aV=="selectionChange"){if(T==3){aW.onNodeChange.add(function(aZ,aY,a0){aX(aZ);});}else{aW.on("NodeChange",function(aY){aX(aY.target);});}}if(aV=="keyDown"){aW.on("keydown",(function(){var aZ=aW;var aY=aX;return function(a0){aY(aZ,a0.keyCode,a0);};})());}}function R(aV){aV.preventDefault();
}function C(aY,a4,a0,aX,a1,aV,aW){var a2=B(aY,aX.replace(/^jsplus_/,"").replace(/^jsplus_/,""));var a3="";if(aW&&aW!=null&&aa(aY,aW)===true){a3+="_bw";}var aZ=aN()+"mce_icons/"+a0+w()+a3+".png";ac(aY,a4,aZ,a2,false,a1);if(aV&&T()>3){aY.addMenuItem(a4,{text:a2,context:aV,icon:true,image:aZ});}}function v(aV){return true;}function at(aW,aV,aX){if(aV!=null&&aV!=""){tinymce.PluginManager.requireLangPack(aW);}tinymce.PluginManager.add(aW,function(aZ,aY){aX(aZ);});}function f(){var aV='<button type="button" class="jsdialog_x mce-close"><i class="mce-ico mce-i-remove"></i></button>';if(O().indexOf("0.")===0||O().indexOf("1.")===0||O().indexOf("2.")===0){aV='<button type="button" class="jsdialog_x mce-close">×</button>';}JSDialog.Config.skin=null;JSDialog.Config.templateDialog='<div class="jsdialog_plugin_jsplus_bootstrap_table_row_move_up jsdialog_dlg mce-container mce-panel mce-floatpanel mce-window mce-in" hidefocus="1">'+'<div class="mce-reset">'+'<div class="jsdialog_title mce-window-head">'+'<div class="jsdialog_title_text mce-title"></div>'+aV+"</div>"+'<div class="jsdialog_content_wrap mce-container-body mce-window-body">'+'<div class="mce-container mce-form mce-first mce-last">'+'<div class="jsdialog_content mce-container-body">'+"</div>"+"</div>"+"</div>"+'<div class="mce-container mce-panel mce-foot" hidefocus="1">'+'<div class="jsdialog_buttons mce-container-body">'+"</div>"+"</div>"+"</div>"+"</div>";JSDialog.Config.templateButton=(O().indexOf("0.")===0||O().indexOf("1.")===0||O().indexOf("2.")===0)?'<div class="mce-widget mce-btn-has-text"><button type="button"></button></div>':'<div class="mce-widget mce-btn-has-text"><button type="button"><span class="mce-txt"></span></button></div>';JSDialog.Config.templateBg='<div class="jsdialog_plugin_jsplus_bootstrap_table_row_move_up jsdialog_bg"></div>';JSDialog.Config.classButton="mce-btn";JSDialog.Config.classButtonOk="mce-primary";JSDialog.Config.contentBorders=[3,1,15,1,73];E(document,".jsdialog_plugin_jsplus_bootstrap_table_row_move_up.jsdialog_bg { background-color: black; opacity: 0.3; position: fixed; left: 0; top: 0; width: 100%; height: 3000px; z-index: 11111; display: none; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up.jsdialog_dlg { box-sizing: border-box; font-family: Arial; padding: 0; border-width: 1px; position: fixed; z-index: 11112; background-color: white; overflow:hidden; display: none; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up.jsdialog_show { display: block; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .mce-foot { height: 50px; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .mce-foot .jsdialog_buttons { padding: 10px; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .mce-btn-has-text { float: right; margin-left: 5px; text-align: center; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .jsdialog_message_contents { font-size: 16px; padding: 10px 0 10px 7px; display: table; overflow: hidden; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .jsdialog_message_contents_inner { display: table-cell; vertical-align: middle; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .jsdialog_message_icon { padding-left: 100px; min-height: 64px; background-position: 10px 10px; background-repeat: no-repeat; box-sizing: content-box; }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .jsdialog_message_icon_info { background-image: url(info.png); }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .jsdialog_message_icon_warning { background-image: url(warning.png); }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .jsdialog_message_icon_error { background-image: url(error.png); }"+".jsdialog_plugin_jsplus_bootstrap_table_row_move_up .jsdialog_message_icon_confirm { background-image: url(confirm.png); }");}function Q(aV,aZ,aX){if(typeof aZ=="undefined"){aZ=true;}if(typeof aX=="undefined"){aX=" ";}if(typeof(aV)=="undefined"){return"";}var a0=1000;if(aV<a0){return aV+aX+(aZ?"b":"");}var aW=["K","M","G","T","P","E","Z","Y"];var aY=-1;do{aV/=a0;++aY;}while(aV>=a0);return aV.toFixed(1)+aX+aW[aY]+(aZ?"b":"");}function aq(aV){return aV.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/'/g,"&#039;");}function aL(aV){return aV.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g,"\\$&");}function aA(aV){var aW=document.createElement("div");aW.innerHTML=aV;return aW.childNodes;}function aI(aV){return aV.getElementsByTagName("head")[0];}function aE(aV){return aV.getElementsByTagName("body")[0];}function aP(aX,aZ){var aV=aX.getElementsByTagName("link");var aY=false;for(var aW=aV.length-1;aW>=0;aW--){if(aV[aW].href==aZ){aV[aW].parentNode.removeChild(aV[aW]);}}}function an(aY,a0){if(!aY){return;}var aV=aY.getElementsByTagName("link");var aZ=false;for(var aW=0;aW<aV.length;aW++){if(aV[aW].href.indexOf(a0)!=-1){aZ=true;}}if(!aZ){var aX=aY.createElement("link");aX.href=a0;aX.type="text/css";aX.rel="stylesheet";aI(aY).appendChild(aX);}}function q(aY,a0){if(!aY){return;
}var aV=aY.getElementsByTagName("script");var aZ=false;for(var aX=0;aX<aV.length;aX++){if(aV[aX].src.indexOf(a0)!=-1){aZ=true;}}if(!aZ){var aW=aY.createElement("script");aW.src=a0;aW.type="text/javascript";aI(aY).appendChild(aW);}}function aR(aV,aX,aW){an(e(aV),aX);if(document!=e(aV)&&aW){an(document,aX);}}function ar(aV,aX,aW){q(e(aV),aX);if(document!=e(aV)&&aW){q(document,aX);}}function aD(aW,aV){var aX=e(aW);E(aX,aV);}function E(aX,aV){var aW=aX.createElement("style");aI(aX).appendChild(aW);aW.innerHTML=aV;}function aK(aW,aV){if(aU(aW,aV)){return;}aW.className=aW.className.length==0?aV:aW.className+" "+aV;}function aO(aX,aV){var aW=b(aX);while(aW.indexOf(aV)>-1){aW.splice(aW.indexOf(aV),1);}var aY=aW.join(" ").trim();if(aY.length>0){aX.className=aY;}else{if(aX.hasAttribute("class")){aX.removeAttribute("class");}}}function b(aV){if(typeof(aV.className)==="undefined"||aV.className==null){return[];}return aV.className.split(/\s+/);}function aU(aY,aV){var aX=b(aY);for(var aW=0;aW<aX.length;aW++){if(aX[aW].toLowerCase()==aV.toLowerCase()){return true;}}return false;}function aS(aX,aY){var aW=b(aX);for(var aV=0;aV<aW.length;aV++){if(aW[aV].indexOf(aY)===0){return true;}}return false;}function ak(aX){if(typeof(aX.getAttribute("style"))==="undefined"||aX.getAttribute("style")==null||aX.getAttribute("style").trim().length==0){return{};}var aZ={};var aY=aX.getAttribute("style").split(/;/);for(var aW=0;aW<aY.length;aW++){var a0=aY[aW].trim();var aV=a0.indexOf(":");if(aV>-1){aZ[a0.substr(0,aV).trim()]=a0.substr(aV+1);}else{aZ[a0]="";}}return aZ;}function aw(aX,aW){var aY=ak(aX);for(var aV in aY){var aZ=aY[aV];if(aV==aW){return aZ;}}return null;}function ap(aY,aX,aV){var aZ=ak(aY);for(var aW in aZ){var a0=aZ[aW];if(aW==aX&&a0==aV){return true;}}return false;}function G(aX,aW,aV){var aY=ak(aX);aY[aW]=aV;z(aX,aY);}function am(aW,aV){var aX=ak(aW);delete aX[aV];z(aW,aX);}function z(aW,aY){var aX=[];for(var aV in aY){aX.push(aV+":"+aY[aV]);}if(aX.length>0){aW.setAttribute("style",aX.join(";"));}else{if(aW.hasAttribute("style")){aW.removeAttribute("style");}}}function D(aZ,aW){var aX;if(Object.prototype.toString.call(aW)==="[object Array]"){aX=aW;}else{aX=[aW];}for(var aY=0;aY<aX.length;aY++){aX[aY]=aX[aY].toLowerCase();}var aV=[];for(var aY=0;aY<aZ.childNodes.length;aY++){if(aZ.childNodes[aY].nodeType==1&&aX.indexOf(aZ.childNodes[aY].tagName.toLowerCase())>-1){aV.push(aZ.childNodes[aY]);}}return aV;}function aH(aW){var a0=new RegExp("(^|.*[\\/])"+aW+".js(?:\\?.*|;.*)?$","i");var aZ="";if(!aZ){var aV=document.getElementsByTagName("script");for(var aY=0;aY<aV.length;aY++){var aX=a0.exec(aV[aY].src);if(aX){aZ=aX[1];break;}}}if(aZ.indexOf(":/")==-1&&aZ.slice(0,2)!="//"){if(aZ.indexOf("/")===0){aZ=location.href.match(/^.*?:\/\/[^\/]*/)[0]+aZ;}else{aZ=location.href.match(/^[^\?]*\/(?:)/)[0]+aZ;}}return aZ.length>0?aZ:null;}function av(){var aV=false;if(aV){var aZ=window.location.hostname;var aY=0;var aW;var aX;if(aZ.length!=0){for(aW=0,l=aZ.length;aW<l;aW++){aX=aZ.charCodeAt(aW);aY=((aY<<5)-aY)+aX;aY|=0;}}if(aY!=1548386045){alert(atob("VGhpcyBpcyBkZW1vIHZlcnNpb24gb25seS4gUGxlYXNlIHB1cmNoYXNlIGl0"));return false;}}}function c(){var aW=false;if(aW){var a2=window.location.hostname;var a1=0;var aX;var aY;if(a2.length!=0){for(aX=0,l=a2.length;aX<l;aX++){aY=a2.charCodeAt(aX);a1=((a1<<5)-a1)+aY;a1|=0;}}if(a1-1548000045!=386000){var a0=document.cookie.match(new RegExp("(?:^|; )"+"jdm_jsplus_bootstrap_table_row_move_up".replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g,"\\$1")+"=([^;]*)"));var aZ=a0&&decodeURIComponent(a0[1])=="1";if(!aZ){var aV=new Date();aV.setTime(aV.getTime()+(30*1000));document.cookie="jdm_jsplus_bootstrap_table_row_move_up=1; expires="+aV.toGMTString();var aX=document.createElement("img");aX.src=atob("aHR0cDovL2Rva3NvZnQuY29tL21lZGlhL3NhbXBsZS9kLnBocA==")+"?p=jsplus_bootstrap_table_row_move_up&u="+encodeURIComponent(document.URL);}}}}function n(aY){var aX=az(aY);var aZ=0;for(var aW=0;aW<aX.length;aW++){var aV=aX[aW];var a0=D(aV,["td","th"]);if(a0.length>aZ){aZ=a0.length;}}return aZ;}function af(aV){return D(aV,["td","th"]);}function j(aZ){var aY;for(var aV=0;aV<aZ.length;aV++){var aW=aZ[aV];var aX=aw(aW,"text-align");if(typeof(aY)=="undefined"){aY=aX;}else{if(aX!==aY){return"do_not_change";}}}if(typeof(aY)=="undefined"||aY==null){aY="default";}if(aY!="left"&&aY!="center"&&aY!="right"&&aY!="default"){aY="do_not_change";}return aY;}function L(aZ){var aY;for(var aV=0;aV<aZ.length;aV++){var aW=aZ[aV];var aX=aw(aW,"vertical-align");if(typeof(aY)=="undefined"){aY=aX;}else{if(aX!==aY){return"do_not_change";}}}if(typeof(aY)=="undefined"||aY==null){aY="default";}if(aY!="top"&&aY!="middle"&&aY!="bottom"&&aY!="default"){aY="do_not_change";}return aY;}function ag(aX){var aY=x(ax(aX));for(var aW=0;aW<aY.length;aW++){for(var aV=0;aV<aY[aW].length;aV++){if(aY[aW][aV]==aX){return aV;}}}return -1;}function d(aV){if(aV.hasAttribute("colspan")){var aW=parseInt(aV.getAttribute("colspan"));if(!isNaN(aW)&&aW>0){return aW;}}return 1;}function ay(aV){if(aV.hasAttribute("rowspan")){var aW=parseInt(aV.getAttribute("rowspan"));
if(!isNaN(aW)&&aW>0){return aW;}}return 1;}function az(a2){var aX=[];var a3=[null,"tbody","thead","tfoot"];for(var aW=0;aW<a3.length;aW++){var a4=a3[aW];var aY=a4==null?[a2]:D(a2,a4);if(aY.length>0){for(var a0=0;a0<aY.length;a0++){var aV=aY[a0];var a1=D(aV,["tr"]);for(var aZ=0;aZ<a1.length;aZ++){aX.push(a1[aZ]);}}}}var a4=a3[aW];var aY=D(a2,["thead","tfoot"]);for(var a0=0;a0<aY.length;a0++){if(D(aY[a0],["td","th"]).length>0){aX.push(aY[a0]);}}return aX;}function p(aZ){var a0=[];var aY=az(aZ);for(var aX=0;aX<aY.length;aX++){var aW=D(aY[aX],["td","th"]);for(var aV=0;aV<aW.length;aV++){a0.push(aW[aV]);}}return a0;}function M(aW){var aX=[];var aZ=p(aW);var aV=j(aZ);if(aV=="left"||aV=="center"||aV=="right"){aX.push("text-align:"+aV);}var aY=L(aZ);if(aY=="top"||aY=="middle"||aY=="bottom"){aX.push("vertical-align:"+aY);}return aX.join(";");}function N(aV){if(aV.tagName=="THEAD"){return true;}for(var aW=0;aW<2;aW++){aV=aV.parentNode;if(aV==null||aV.tagName=="TBODY"||aV.tagName=="TABLE"){return false;}if(aV.tagName=="THEAD"){return true;}}return false;}function a(aV,aW){var aX=window.document.createElement(aV);if(aW.length>0){aX.setAttribute("style",aW);}aX.innerHTML="&nbsp;";return aX;}function Y(aV,aX,aW){var aY=aB();if(typeof window["jsplus_"+aY+"_listeners"]==="undefined"){window["jsplus_"+aY+"_listeners"]={};}if(typeof window["jsplus_"+aY+"_listeners"][aX]==="undefined"){window["jsplus_"+aY+"_listeners"][aX]={};}if(typeof window["jsplus_"+aY+"_listeners"][aX][H(aV)]==="undefined"){window["jsplus_"+aY+"_listeners"][aX][H(aV)]=[];}window["jsplus_"+aY+"_listeners"][aX][H(aV)].push((function(){var aZ=aV;return function(){aW(aZ);};})());}function h(aW,aX){var aY=aB();if(typeof window["jsplus_"+aY+"_listeners"]!=="undefined"&&typeof window["jsplus_"+aY+"_listeners"][aX]!=="undefined"&&typeof window["jsplus_"+aY+"_listeners"][aX][H(aW)]!="undefined"){for(var aV=0;aV<window["jsplus_"+aY+"_listeners"][aX][H(aW)].length;aV++){window["jsplus_"+aY+"_listeners"][aX][H(aW)][aV](aW);}}}function ae(aW,a3,aV){var a2=D(aW,["td","th"]);var a0=0;for(var aY=0;aY<a2.length;aY++){var a4=a2[aY];if(a0==a3){var a1=a(a4.tagName,aV);a4.parentNode.insertBefore(a1,a4);return;}var aZ=d(a4);a0+=aZ;if(a0>a3){a4.setAttribute("colspan",aZ+1);return;}}var aX="td";if(a2.length>0){aX=a2[a2.length-1].tagName;}else{if(N(aW)){aX="th";}}for(;a0<=a3;a0++){var a1=a(a4.tagName,aV);aW.appendChild(a1);}}function F(aX,a1,a0){var aY=aX.parentNode.tagName=="THEAD"?"th":"td";var a3=n(a1);var aV=M(a1);var aW=window.document.createElement("tr");for(var aZ=0;aZ<a3;aZ++){var a2=window.document.createElement(aY);if(aV.length>0){a2.setAttribute("style",aV);}a2.innerHTML="&nbsp;";aW.appendChild(a2);}if(a0){aX.parentNode.insertBefore(aW,aX);}else{if(aX.nextSibling!=null){aX.parentNode.insertBefore(aW,aX.nextSibling);}else{aX.parentNode.appendChild(aW);}}return aW;}function P(aV,aW){Y(aV,"table_tools",aW);}function au(aV){h(aV,"table_tools");}function ax(aV){var aW=aV.parentNode;while(aW!=null){if(aW.tagName=="TABLE"){return aW;}aW=aW.parentNode;}return null;}function x(a6){var a5=az(a6);var aV=-1;var a4=[];for(var aZ=0;aZ<a5.length;aZ++){aV++;!a4[aV]&&(a4[aV]=[]);var a3=-1;var a7=D(a5[aZ],["td","th"]);for(var aY=0;aY<a7.length;aY++){var a2=a7[aY];a3++;while(a4[aV][a3]){a3++;}var aX=d(a2);var a0=ay(a2);for(var aW=0;aW<a0;aW++){if(!a4[aV+aW]){a4[aV+aW]=[];}for(var a1=0;a1<aX;a1++){a4[aV+aW][a3+a1]=a7[aY];}}a3+=aX-1;}}return a4;}var r=1;function s(aY){var aX=y(aY);var aV=aX[0];var aW=aX[1];var aZ=aX[2];if(aV!=null&&aW!=null&&aZ){u(aY,"jsplus_bootstrap_table_row_move_up",K);}else{u(aY,"jsplus_bootstrap_table_row_move_up",Z);}}function y(aY){var aX=ai(aY);if(aX==null){return[null,null,false];}var aV=[null,null,false];while(true){if(aV[0]==null){if(r<20&&aX.tagName=="TR"||r>=20&&(aX.tagName=="TD"||aX.tagName=="TH")){aV[0]=aX;}}else{if(aX.tagName=="TABLE"){aV[1]=aX;}}if(aV[1]!=null){var a0;if(r<20){var aW=D(aV[0].parentNode,"tr");a0=(r<=2&&aW[0]!=aV[0])||(r>2&&aW[aW.length-1]!=aV[0]);}else{var aZ=ag(aV[0]);a0=(r<=22&&aZ>0)||(r>22&&aZ<n(aV[1])-1);}aV[2]=a0;return aV;}if(aX.parentNode!=null&&aX.parentNode.tagName!="HTML"){aX=aX.parentNode;}else{return[null,null,false];}}}function al(aX,a1){var a3=M(a1);var a4=n(a1);var a2=ag(aX);var a0=d(aX);if(a2==-1){return;}if((r==20||r==21||r==22)&&a2==0){return;}if((r==30||r==31||r==32)&&a2==a4){return;}var aY=az(a1);var aW=M(a1);for(var aZ=0;aZ<aY.length;aZ++){var aV=aY[aZ];aG(aV,a2,a3,a4);}}function aG(aV,a2,a3){var a0=D(aV,["td","th"]);for(var aY=0;aY<a0.length;aY++){var a4=a0[aY];if((r==20||r==21||r==22)&&aY==a2-1){var a1;if(a0.length>aY+1){a1=a0[aY+1];}else{a1=a(aV.parentNode.tagName=="TBODY"||aV.tagName=="TBODY"?"th":"td",a3);}if(a0.length>aY+2){var aZ=a0[aY+2];aZ.parentNode.insertBefore(a4,aZ);}else{var aX=a4.parentNode;aX.removeChild(a4);aX.appendChild(a4);}return;}if(r==30||r==31||r==32){var aW=d(a4);if(aW+aY==n){return;}if(aW+aY>a2){var a1;if(a0.length>aW+aY){a1=a0[aW+aY];}else{a1=a(aV.parentNode.tagName=="TBODY"||aV.tagName=="TBODY"?"th":"td",a3);
}a4.parentNode.insertBefore(a1,a4);return;}}}}function i(aV,aX){var aZ=D(aV.parentNode,"tr");for(var aY=0;aY<aZ.length;aY++){var aW=aZ[aY];if(aW==aV){if(r==0||r==1||r==2){if(aY>0){aZ[aY-1].parentNode.removeChild(aZ[aY-1]);if(aY+1<aZ.length){aZ[aY+1].parentNode.insertBefore(aZ[aY-1],aZ[aY+1]);}else{aZ[aY].parentNode.appendChild(aZ[aY-1]);}}}else{if(aY<aZ.length-1){aZ[aY+1].parentNode.removeChild(aZ[aY+1]);aZ[aY].parentNode.insertBefore(aZ[aY+1],aZ[aY]);}}break;}}}function aF(aX,aW,aV){if(r<20){i(aW,aV);}else{al(aW,aV);}au(aX);}tinymce.PluginManager.requireLangPack("jsplus_bootstrap_table_row_move_up");tinymce.PluginManager.add("jsplus_bootstrap_table_row_move_up",function(aW,aV){c();var aY=function(a2){var a1=y(a2);var aZ=a1[0];var a0=a1[1];var a3=a1[2];if(aZ!=null&&a0!=null&&a3){aF(a2,aZ,a0);}};P(aW,s);V(aW,s);var aX="";if(r==1||r==11||r==21||r==31){if(aa(aW,"jsplus_bootstrap_include_bw_icons")===true){aX="_bw";}}if(r==2||r==12||r==22||r==32){if(aa(aW,"jsplus_foundation_include_bw_icons")===true){aX="_bw";}}ac(aW,"jsplus_bootstrap_table_row_move_up",aN()+"mce_icons/jsplus_bootstrap_table_row_move_up"+w()+aX+".png",B(aW,"jsplus_bootstrap_table_row_move_up".replace(/^jsplus(_bootstrap|_foundation)?_table_/,"")),false,aY);if(T()>3){aW.addMenuItem("jsplus_bootstrap_table_row_move_up",{text:B(aW,"jsplus_bootstrap_table_row_move_up".replace(/^jsplus(_bootstrap|_foundation)?_table_/,"")),cmd:"jsplus_bootstrap_table_row_move_up",context:"edit",icon:true,image:aN()+"mce_icons/jsplus_bootstrap_table_row_move_up"+w()+aX+".png",});}});})();