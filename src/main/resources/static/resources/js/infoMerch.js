import { getDomain } from "./getDomain.js";

(() => {
  let url = `http://${getDomain}/api/merchandise`;
  let popUpInfoMerch = document.querySelector("#myModalInfoMerch");
  let infoMerchButton = document.querySelectorAll(
    ".product-table-section .info-merch"
  );
  let closeInfoMerch = document.querySelector(
    "#myModalInfoMerch #cancelButton"
  );

  for (let button of infoMerchButton) {
    button.addEventListener("click", () => {
      popUpInfoMerch.classList.add("active");
      let merchandiseId = button.getAttribute("id");
      populatedInfo(merchandiseId);
    });
  }

  function populatedInfo(merchandiseId) {
    let accessToken = localStorage.getItem("jwtToken");
    let requestInfo = new XMLHttpRequest();
    requestInfo.open("GET", `${url}/info?id=${merchandiseId}`);
    requestInfo.setRequestHeader("Authorization", "Bearer " + accessToken);
    requestInfo.send();
    requestInfo.onload = () => {
      let response = JSON.parse(requestInfo.response);

      let merchandiseNameInfoForm = document.querySelector(
        "#myModalInfoMerch .content .name"
      );
      let categoryNameInfoForm = document.querySelector(
        "#myModalInfoMerch .content .category"
      );
      let descriptionInfoForm = document.querySelector(
        "#myModalInfoMerch .content .description"
      );
      let priceInfoForm = document.querySelector(
        "#myModalInfoMerch .content .price"
      );
      let discontinueInfoForm = document.querySelector(
        "#myModalInfoMerch .content .discontinue"
      );

      merchandiseNameInfoForm.textContent = response.merchandiseName;
      categoryNameInfoForm.textContent = response.categoryName;
      descriptionInfoForm.textContent = response.description;
      priceInfoForm.textContent = response.price;
      discontinueInfoForm.textContent = response.discontinue;
    };
  }

  closeInfoMerch.addEventListener("click", () => {
    let merchandiseNameInfoForm = document.querySelector(
      "#myModalInfoMerch .content .name"
    );
    let categoryNameInfoForm = document.querySelector(
      "#myModalInfoMerch .content .category"
    );
    let descriptionInfoForm = document.querySelector(
      "#myModalInfoMerch .content .description"
    );
    let priceInfoForm = document.querySelector(
      "#myModalInfoMerch .content .price"
    );
    let discontinueInfoForm = document.querySelector(
      "#myModalInfoMerch .content .discontinue"
    );
    merchandiseNameInfoForm.innerHTML = "";
    categoryNameInfoForm.innerHTML = "";
    descriptionInfoForm.innerHTML = "";
    priceInfoForm.innerHTML = "";
    discontinueInfoForm.innerHTML = "";

    popUpInfoMerch.classList.remove("active");
  });
})();
