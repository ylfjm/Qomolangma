function addWindow(divId, url, size, dialogOnHidden, dialogOnShown, position) {
    var html = $.ajax({
        url : url,
        async : false
    }).responseText;
    var content = $("<div id=" + divId + "></div>").append(html);
    var buttons = [{
        label: 'save',
        cssClass: 'btn-primary',
        action: function(dialogItself){
            dialogItself.close();
        }
    }];
    return addDialog(divId, content, buttons, size, dialogOnHidden, dialogOnShown, position);
}

function addDialog(divId, content, buttons, size, dialogOnHidden, dialogOnShown, position) {
    var newBootstrapDialog = BootstrapDialog.show({
        title: "",
        type: BootstrapDialog.TYPE_DEFAULT,
        animate: false,
        draggable: true,
        closable: true,
        closeByBackdrop: false,
        closeByKeyboard: false,
        message: content,
        onshown: function(dialogRef) {
            dialogRef.getModalHeader().css("padding", 5);
            dialogRef.getModalFooter().css("padding", 8);
            var offsetHorizontal = 0;
            if (position) {
                offsetHorizontal = position;
            }
            dialogRef.getModalDialog().css("top",
                    (document.documentElement.clientHeight - dialogRef.getModalDialog().height()) / 2 + offsetHorizontal);
            if (size) {
                dialogRef.getModalDialog().css("width", size[0]);
                dialogRef.getModalContent().css("height", size[1]);
            }
            if (dialogOnShown) {
                dialogOnShown();
            }
            if ($("#" + divId)[0]) {
                $("#" + divId)[0].dialogObj = dialogRef;
            }
        },
        onhidden: function(dialogRef) {
            if (dialogOnHidden) {
                dialogOnHidden();
            }
        },
        buttons: buttons
    });
    return newBootstrapDialog;
}