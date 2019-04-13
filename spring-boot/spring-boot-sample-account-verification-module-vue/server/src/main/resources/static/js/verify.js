var Verify = {
    init: function () {
    $('#registerForm').validate({
        rules: {
            login: {
                required: true,
                minlength: 5,
                remote: {
                    url: '/api/checkIfExistUser',
                    type: 'get',
                    data: {
                        'login': function () {
                            return $('#login').val()
                        }
                    },
                    dataFilter: function (data,type) {
                        return checkLoginOrEmailNotInUse(data);
                    }
                }
            },
            email: {
                required: true,
                remote: {
                    url: '/api/checkIfExistUser',
                    type: 'get',
                    data: {
                        'email': function() { return $('#email').val() }
                    },
                    dataFilter: function (data,type) {
                        return checkLoginOrEmailNotInUse(data);
                    }
                }
            },
            password: {
                required: true,
                minlength: 5
            },
            matchPassword: {
                required: true,
                minlength: 5,
                equalTo: '#password'
            }
        },
        messages: {
            login: {
                required: /*[[#{message.login.required}]]*/,
                remote: /*[[#{message.login.alreadyExist}]]*/
            },
            email: {
                required: /*[[#{message.email.required}]]*/,
                remote: /*[[#{message.email.alreadyExist}]]*/
            },
            password: {
                required: /*[[#{message.password.required}]]*/
            },
            matchPassword: {
                required: /*[[#{message.matchPassword.required}]]*/,
                equalTo: /*[[#{message.matchPassword.equalTo}]]*/
            }
        },
        submitHandler: function (form) {
            $.ajax({
                url: form.action,
                type: form.method,
                data: $(form).serialize(),
                success: function(response) {
                    /*TODO: 弹出模态框,再转到默认页面*/
                    window.location.href = serverContext + "admin/index.html";
                }
            });
        }
    });

    }
}

function checkLoginOrEmailNotInUse(data) {
    var jsonVal = jQuery.parseJSON(data);
    return !jsonVal.alreadyExistUser;
}
