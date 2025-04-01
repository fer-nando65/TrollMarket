import { getDomain } from "./domain";

(() => {
  let url = `http://${getDomain}/api/profile`;
  let popUpModal = document.querySelector("#myModalBalance");
  let closeButton = document.querySelector("#cancelButton");
  let showButton = document.querySelector(".user-info-details .add-funds");
  let addBalanceButton = document.querySelector("#myModalBalance #addButton");
  let errorSpan = document.querySelector("#myModalBalance .span-errors");
  let inputUsername = document.querySelector("#myModalBalance #username");
  let inputAmount = document.querySelector("#myModalBalance #amount");

  showButton.addEventListener("click", () => {
    popUpModal.classList.add("active");
  });

  addBalanceButton.addEventListener("click", () => {
    let accessToken = localStorage.getItem("jwtToken");
    inputUsername = inputUsername.value;
    inputAmount = inputAmount.value;

    let dto = {
      username: inputUsername,
      balance: inputAmount,
    };

    let postBalance = new XMLHttpRequest();
    postBalance.open("POST", `${url}`);
    postBalance.setRequestHeader(
      "Content-Type",
      "application/json;charset=UTF-8"
    );
    postBalance.setRequestHeader("Authorization", "Bearer " + accessToken);
    postBalance.send(JSON.stringify(dto));
    postBalance.onload = () => {
      if (postBalance.status === 200) {
        console.log("Balance Saved");
        window.location.reload();
      } else {
        let errorText = postBalance.response;
        errorSpan.textContent = errorText;
      }
    };
  });

  closeButton.addEventListener("click", () => {
    errorSpan.innerHTML = "";
    inputAmount.innerHTML = "";
    popUpModal.classList.remove("active");
  });
})();
