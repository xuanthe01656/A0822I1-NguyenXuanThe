$(document).ready(function(){
			$(".form").validate({
				ignore:[],
				rules:{
					username: {
						required: true,
						minlength: 6,
						maxlength: 32,
					},
					password: {
						required:  true,
						minlength: 6,
					},
					repassword:{
						equalTo: "#password"
					},
					fullname:{
						required: true,
					},
					address:{
						required: true,
					},
					birthday:{
						required: true,
						dateISO: true,
					},
					phone:{
						required: true,
						digits: true,
						maxlength: 11,
						
					},
					email: {
						
						required: true,
						email: true,
						minlength: 11,
						maxlength: 150,
					},
					facebook:{
						url: true,
					},
					moreinfo:{
						required: true,
					},
					
				},
				messages:{
					username: {
						required: " Hãy nhập vào username.",
						minlength: " Hãy nhập vào nhiều hơn 6 ký tự và ít hơn 32 ký tự.",
						maxlength: " Hãy nhập vào ít hơn 32 ký tự.",
					},
					password: {
						required: " Hãy nhập vào password.",
						minlength: " Hãy nhập vào nhiều hơn 6 ký tự.",
					},
					repassword:{
						equalTo: "Vui lòng nhập trùng với mật khẩu."
					},
					fullname:{
						required: "Hãy nhập vào tên đầy đủ",
					},
					address:{
						required: "Hãy nhập vào địa chỉ.",
					},
					birthday:{
						required: "Hãy nhập vào ngày tháng năm sinh",
						dateISO: "Hãy nhập vào đúng định dạng yyyy/mm/dd.",
					},
					phone:{
						required: "Hãy nhập vào số điện thoại",
						digits: " Hãy nhập là số nguyên",
						maxlength: "Tối đa 11 số.",
					},
					email: {
						required: "Hãy nhập vào email",
						email: "Hãy nhập đúng định dạng",
						minlength: " Hãy nhập vào nhiều hơn 11 ký tự và ít hơn 150 ký tự.",
						maxlength: " Hãy nhập vào ít hơn 150 ký tự.",
					},
					facebook:{
						url: "Hãy nhập đúng định dạng",
					},
					moreinfo:{
						required: "Hãy nhập giới thiệu",
					},
				},
			});
		});
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