/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.uiColor = '#9AB8F3';

    //是否强制复制来的内容去除格式 plugins/pastetext/plugin.js   
    config.forcePasteAsPlainText =false//不去除  
    //是否使用等标签修饰或者代替从word文档中粘贴过来的内容 plugins/pastefromword/plugin.js    
    config.pasteFromWordKeepsStructure = false;  
    //从word中粘贴内容时是否移除格式 plugins/pastefromword/plugin.js  
    config.pasteFromWordRemoveStyle = false  
    config.pasteFromWordRemoveFontStyles = false; 
    //
    config.allowedContent = true;
    config.format_p = { element: 'p', attributes: { 'class': 'normalPara' } };
    
    config.image_previewText='';
    if(typeof  window.uploadUrl != "undefined"){
    	config.filebrowserImageUploadUrl= window.uploadUrl;
    }
    
    
    
    config.toolbar = 'FULL';//把默认工具栏改为‘MyToolbar’   
    
    config.toolbar_zixun2 =   
    [   
        ['NewPage','Preview'],   
        ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],   
        ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],   
        ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],   
        '/',   
        ['Styles','Format'],   
        ['Bold','Italic','Strike'],   
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],   
        ['Link','Unlink','Anchor'],   
        ['Maximize','-','About']   
    ]; 
    config.toolbar_zixun =   
        [   
            ['BGColor','TextColor'],
            ['Bold','Italic','Underline','Strike'],
            ['Subscript','Superscript','NumberedList','BulletedList'],
            ['Undo','Redo'],
            ['Cut','Copy','Paste','PasteText','PasteFromWord'],
            ['Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight'],
            ['Link','UnLink','Image','Table','Smilery','Blockquote','Maximize']
        ]; 
    config.toolbar_basic =   
        [   
            ['Bold','Italic','Underline','Strike'],
            ['Table','Smilery','Blockquote']
        ];



};
