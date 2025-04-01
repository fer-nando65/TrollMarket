import { getDomain } from "./domain";

(() => {
  let url = `http://${getDomain}/api/shop`;
  let popUpAddCart = document.querySelector("#myModalAddCart");
  let closeCartButton = document.querySelector("#myModalAddCart #cancelButton");
  let addButton = document.querySelector("#myModalAddCart #addButton");
  let amountErrorSpan = document.querySelector("#myModalAddCart .amount-error");
  let shipperIdErrorSpan = document.querySelector(
    "#myModalAddCart .shipperId-error"
  );
  let currentUsername = document.querySelector("#myModalAddCart #username");
  let amountItem = document.querySelector("#myModalAddCart #amount");
  let selectedShipper = document.querySelector("#myModalAddCart #shipperId");
  let addCartButton = document.querySelectorAll(
    ".shop-table-section .add-to-cart"
  );

  for (let button of addCartButton) {
    button.addEventListener("click", () => {
      popUpAddCart.classList.add("active");
      let merchandiseId = button.getAttribute("id");
      addCartItem(merchandiseId);
    });
  }

  function addCartItem(merchandiseId) {
    let urlCart = `http://${getDomain}/api/cart`;
    let accessToken = localStorage.getItem("jwtToken");
    addButton.addEventListener("click", () => {
      let id = merchandiseId;

      currentUsername = currentUsername.value;
      amountItem = amountItem.value;
      selectedShipper = selectedShipper.value;

      let dto = {
        username: currentUsername,
        merchandiseId: parseInt(id),
        amount: parseInt(amountItem),
        shipperId: parseInt(selectedShipper),
      };

      let requestPostCart = new XMLHttpRequest();
      requestPostCart.open("POST", `${urlCart}`);
      requestPostCart.setRequestHeader(
        "Content-Type",
        "application/json;charset=UTF-8"
      );
      requestPostCart.setRequestHeader(
        "Authorization",
        "Bearer " + accessToken
      );
      requestPostCart.send(JSON.stringify(dto));
      requestPostCart.onload = () => {
        if (requestPostCart.status === 200) {
          console.log("Saved");
          window.location.reload();
        } else {
          var errorResponse = JSON.parse(requestPostCart.response);
          amountErrorSpan.textContent = errorResponse.amountError;
          shipperIdErrorSpan.textContent = errorResponse.shipperIdError;
        }
      };
    });
  }

  closeCartButton.addEventListener("click", () => {
    let amountItem = document.querySelector("#myModalAddCart #amount");

    amountItem.value = "";
    amountErrorSpan.innerHTML = "";
    shipperIdErrorSpan.innerHTML = "";
    popUpAddCart.classList.remove("active");
  });

  //   ======================================================

  let popUpDetailInfo = document.querySelector("#myModalDetailInfo");
  let detailInfoButton = document.querySelectorAll(
    ".shop-table-section .detail-info"
  );
  let closeDetailInfo = document.querySelector(
    "#myModalDetailInfo #cancelButtonInfo"
  );

  for (let button of detailInfoButton) {
    button.addEventListener("click", () => {
      popUpDetailInfo.classList.add("active");
      let merchandiseId = button.getAttribute("id");
      populatedShopInfo(merchandiseId);
    });
  }

  function populatedShopInfo(merchandiseId) {
    let accessToken = localStorage.getItem("jwtToken");
    let requestInfo = new XMLHttpRequest();
    requestInfo.open("GET", `${url}/info?id=${merchandiseId}`);
    requestInfo.setRequestHeader("Authorization", "Bearer " + accessToken);
    requestInfo.send();
    requestInfo.onload = () => {
      let response = JSON.parse(requestInfo.response);

      let spanMerchandiseName = document.querySelector(
        "#myModalDetailInfo .merchandiseName"
      );
      let spanCategory = document.querySelector("#myModalDetailInfo .category");
      let spanDescription = document.querySelector(
        "#myModalDetailInfo .description"
      );
      let spanPrice = document.querySelector("#myModalDetailInfo .price");
      let spanSellerName = document.querySelector(
        "#myModalDetailInfo .sellerName"
      );

      spanMerchandiseName.textContent = response.merchandiseName;
      spanCategory.textContent = response.categoryName;
      spanDescription.textContent = response.description;
      spanPrice.textContent = response.price;
      spanSellerName.textContent = response.sellerName;
    };
  }

  closeDetailInfo.addEventListener("click", () => {
    let spanMerchandiseName = document.querySelector(
      "#myModalDetailInfo .merchandiseName"
    );
    let spanCategory = document.querySelector("#myModalDetailInfo .category");
    let spanDescription = document.querySelector(
      "#myModalDetailInfo .description"
    );
    let spanPrice = document.querySelector("#myModalDetailInfo .price");
    let spanSellerName = document.querySelector(
      "#myModalDetailInfo .sellerName"
    );

    spanMerchandiseName.innerHTML = "";
    spanCategory.innerHTML = "";
    spanDescription.innerHTML = "";
    spanPrice.innerHTML = "";
    spanSellerName.innerHTML = "";

    popUpDetailInfo.classList.remove("active");
  });
})();
