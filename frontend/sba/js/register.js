$('#registerForm').on("submit", function(event){
    event.preventDefault();
    let passwordTest = (/^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,20}$/).test($('#password').val());
    let emailTest = (/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/).test( $('#email').val());
    if (!passwordTest) { alert("password Invalid");}
    else if (!emailTest) {alert("Invalid Email")}
    else if ($('#password').val() !== $('#confirmPassword').val()) {alert("Passwords do not match")}
    else { $('#message').text("Registration Successful") ;}

});
//     let emailEle = $('#email');
//     let emailRegex = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
//     let emailTest = (emailRegex).test( emailEle.val());
//     let passwordEle = $('#password');
//     let passwordRegex= /^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,20}$/;
//     let passwordTest = (passwordRegex).test(passwordEle.val());
//      if (emailTest) {
//         emailEle.removeClass('is-invalid');
//     } else {
//         emailEle.addClass('is-invalid');
//     }
//     if (passwordTest) {
//         password.removeClass('is-invalid');
//     } else {
//         passwordEle.addClass('is-invalid');
//     }
//     // if (emailTest || emailEle.val() == '' || 
//     //     $('#firstName').val() == '' ||
//     //     $('#lastName').val() == '' ||
//     //     $('#password').val() == '' ||
//     //     $('#confirmPassword').val() == '') {
//     //     alert("All fields are required in order to register");
//     //     return false;
//     // }
// });

(function () {
    'use strict'
  
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')
  
    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        form.addEventListener('submit', function (event) {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }
  
          form.classList.add('was-validated')
        }, false)
      })
  })()
