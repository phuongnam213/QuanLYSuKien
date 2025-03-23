$(document).ready(function () {
    $(".veen .rgstr-btn button").click(function () {
        $('.veen .wrapper').addClass('move');
        $(".veen .login-btn button").removeClass('active');
        $(this).addClass('active');
        $('#register-icons').hide();
        $('#login-icons').show();
    });

    $(".veen .login-btn button").click(function () {
        $('.veen .wrapper').removeClass('move');
        $(".veen .rgstr-btn button").removeClass('active');
        $(this).addClass('active');
        $('#login-icons').hide();
        $('#register-icons').show();
    });

    $('#register-icons').show();
    $('#login-icons').hide();

    // Kiểm tra đầu vào theo thời gian thực và hiển thị dấu tích hoặc dấu X
    function validateField(selector, validator, asyncValidator) {
        $(selector).on("input", function() {
            var $this = $(this);
            var isValid = validator($this.val());

            if (isValid && asyncValidator) {
                asyncValidator($this.val()).then(function(isAsyncValid) {
                    updateFieldStatus($this, isAsyncValid);
                });
            } else {
                updateFieldStatus($this, isValid);
            }
        });
    }

    function updateFieldStatus($field, isValid) {
        var formGroup = $field.closest('.form-group');
        if (isValid) {
            formGroup.find('.status-icon.invalid').hide();
            formGroup.find('.status-icon.valid').show();
        } else {
            formGroup.find('.status-icon.valid').hide();
            formGroup.find('.status-icon.invalid').show();
        }
    }

    validateField("#username-register", function(value) {
        return value.length >= 3;
    }, function(value) {
        return $.ajax({
            url: '/check-username',
            data: { username: value },
            method: 'GET'
        }).then(function(response) {
            return response;
        });
    });

    validateField("#password-register", function(value) {
        return value.length >= 6;
    });

    validateField("#confirmPassword", function(value) {
        return value === $("#password-register").val();
    });

    validateField("#email", function(value) {
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        return emailPattern.test(value);
    });

    validateField("#name", function(value) {
        return value.trim() !== "";
    });
});
