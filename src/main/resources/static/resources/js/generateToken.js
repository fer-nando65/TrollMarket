import { getDomain } from "./domain";

(() => {
  let signInButton = document.querySelector(".login-container form button");
  let loginForm = document.querySelector(".login-container form");
  signInButton.addEventListener("click", (event) => {
    event.preventDefault();
    let username = document.querySelector(
      ".login-container form #username"
    ).value;
    let password = document.querySelector(
      ".login-container form #password"
    ).value;
    let role = document.querySelector(".login-container form #role").value;

    let account = {
      username: username,
      password: password,
      role: role,
    };

    let request = new XMLHttpRequest();
    request.open("POST", `http://${getDomain}/api/account/authenticate`);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(account));
    request.onload = () => {
      if (request.status === 200) {
        let response = JSON.parse(request.response);
        localStorage.setItem("jwtToken", response.token);
        loginForm.submit();
      } else {
        window.location.href = "/account/failLogin";
      }
    };
  });
})();
