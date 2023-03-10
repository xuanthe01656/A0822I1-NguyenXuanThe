 
$(document).ready(function() {
		$(".form2").validate({
		rules:{
			username:{
				required: true,
				minlength:4,
			},
			password:{
				required: true,
				minlength:6,
				maxlength: 32,
			}
		},
		messages:{
			username: {
				required: "Hãy nhập vào Username.",
				minlength: "Hãy nhập vào ít nhất 4 ký tự.",
			},
			password: {
				required: "Hãy nhập vào Password.",
				minlength: "Hãy nhập vào nhiều hơn 6 ký tự và ít hơn 32 ký tự.",
				maxlength: "Hãy nhập vào ít hơn 32 ký tự.",
			},	
		},
	});
	
});