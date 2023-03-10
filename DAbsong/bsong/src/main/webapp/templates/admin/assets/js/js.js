   
$(document).ready(function() {
		$(".form").validate({
		ignore: [],
		debug: false,
		rules:{
			name: {
				required: true,
				minlength: 1,
				maxlength: 32,
			},
			
			category: {
				required:  true,
			},
			picture: {
				required:  true,
			},
			preview: {
				required: true,
			},
			detail: {
				required: true,
			},
			username:{
				requered: true,
				minlength:6,
			},
			password:{
				requered: true,
				minlength:6,
				maxlength: 32,
			}
		},
		messages:{
			name: {
				required: "Hãy nhập vào họ tên.",
				minlength: "Hãy nhập vào nhiều hơn 1 ký tự và ít hơn 32 ký tự.",
				maxlength: "Hãy nhập vào ít hơn 32 ký tự.",
			},
			category: {
				required: "Hãy chọn danh mục.",
			},
			picture: {
				required:  "Hãy chọn file.",
			},
			preview: {
				required: "Hãy nhập mô tả",
			},
			detail: {
				required: "Hãy nhập chi tiết",
			},
			username: {
				required: "Hãy nhập vào Username.",
				minlength: "Hãy nhập vào ít nhất 6 ký tự.",
			},
			password: {
				required: "Hãy nhập vào Password.",
				minlength: "Hãy nhập vào nhiều hơn 6 ký tự và ít hơn 32 ký tự.",
				maxlength: "Hãy nhập vào ít hơn 32 ký tự.",
			},	
		},
	});
	
});
/*CKEDITOR.config.toolbar = [
    ['Bold','Italic','Underline','myCombo']
] ;*/
//deal with copying the ckeditor text into the actual textarea
CKEDITOR.on('instanceReady', function () {
    $.each(CKEDITOR.instances, function (instance) {
        CKEDITOR.instances[instance].document.on("keyup", CK_jQ);
        CKEDITOR.instances[instance].document.on("paste", CK_jQ);
        CKEDITOR.instances[instance].document.on("keypress", CK_jQ);
        CKEDITOR.instances[instance].document.on("blur", CK_jQ);
        CKEDITOR.instances[instance].document.on("change", CK_jQ);
    });
});

function CK_jQ() {
    for (instance in CKEDITOR.instances) {
        CKEDITOR.instances[instance].updateElement();
    }
};
