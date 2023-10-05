$(".status_message").delay(4000).slideUp(200, function() {
    $(this).alert('close');
});

/*

$('#password').password({
    closestSelector: '.form-group',
    shortPass: 'La contraseña es muy corta',
    badPass: 'Debil; trate de combinar letras y números',
    goodPass: 'Media; trate de combinar caracteres especiales',
    strongPass: 'Contraseña Fuerte',
    containsField: 'La contraseña contiene su usuario',
    enterPass: 'Ingrese la contraseña',
    showPercent: false,
    showText: true, // shows the text tips
    animate: true, // whether or not to animate the progress bar on input blur/focus
    animateSpeed: 'fast', // the above animation speed
    field: false, // select the match field (selector or jQuery instance) for better password checks
    fieldPartialMatch: true, // whether to check for partials in field
    minimumLength: 4 // minimum password length (below this threshold, the score is 0)
});

var input_password = $("#show_password input");
var icon_password  = $("#show_password i");

var input_confirmar_password = $("#show_confirm_password input");
var icon_confirmar_password  = $("#show_confirm_password i");

var input_actual_password = $("#show_actual_password input");
var icon_actual_password  = $("#show_actual_password i");

icon_actual_password.on('click', function (event) {
    event.preventDefault();

    if (input_actual_password.attr("type") === "text") {
        input_actual_password.attr('type', 'password');
        icon_actual_password.addClass("fa-eye-slash");
        icon_actual_password.removeClass("fa-eye");

    } else if (input_actual_password.attr("type") === "password") {
        input_actual_password.attr('type', 'text');
        icon_actual_password.removeClass("fa-eye-slash");
        icon_actual_password.addClass("fa-eye");
    }
});


icon_confirmar_password.on('click', function (event) {
    event.preventDefault();

    if (input_confirmar_password.attr("type") === "text") {
        input_confirmar_password.attr('type', 'password');
        icon_confirmar_password.addClass("fa-eye-slash");
        icon_confirmar_password.removeClass("fa-eye");

    } else if (input_confirmar_password.attr("type") === "password") {
        input_confirmar_password.attr('type', 'text');
        icon_confirmar_password.removeClass("fa-eye-slash");
        icon_confirmar_password.addClass("fa-eye");
    }
});


icon_password.on('click', function (event) {
    event.preventDefault();

    if (input_password.attr("type") === "text") {
        input_password.attr('type', 'password');
        icon_password.addClass("fa-eye-slash");
        icon_password.removeClass("fa-eye");

    } else if (input_password.attr("type") === "password") {
        input_password.attr('type', 'text');
        icon_password.removeClass("fa-eye-slash");
        icon_password.addClass("fa-eye");
    }
});

*/

/*$(document).ready(function () {
	$('.chosen-select').chosen();
});*/




