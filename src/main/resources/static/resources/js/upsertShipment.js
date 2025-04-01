import { getDomain } from "./getDomain.js";

(() => {
  let url = `http://${getDomain}/api/shipper`;
  let popUpUpsertShipment = document.querySelector("#myModalUpsertShipment");
  let addShipmentButton = document.querySelector(".add-new-shipment");
  let closePopUpUpsert = document.querySelector(
    "#myModalUpsertShipment #cancelButton"
  );
  let companyNameErrorSpan = document.querySelector(
    "#myModalUpsertShipment .companyName-error"
  );
  let priceErrorSpan = document.querySelector(
    "#myModalUpsertShipment .price-error"
  );

  addShipmentButton.addEventListener("click", () => {
    popUpUpsertShipment.classList.add("active");
  });

  let submitShipmentButton = document.querySelector(
    "#myModalUpsertShipment .button-container #addButton"
  );

  submitShipmentButton.addEventListener("click", () => {
    let inputShipperId = document.querySelector(
      "#myModalUpsertShipment #shipperId"
    );
    let inputShipperName = document.querySelector(
      "#myModalUpsertShipment #shipperName"
    );
    let inputShipperPrice = document.querySelector(
      "#myModalUpsertShipment #shipperPrice"
    );
    let inputShipperService = document.querySelector(
      "#myModalUpsertShipment #shipperService"
    );

    inputShipperId = inputShipperId.value;
    inputShipperName = inputShipperName.value;
    inputShipperPrice = inputShipperPrice.value;
    inputShipperService = inputShipperService.checked;

    let dto = {
      shipperId: inputShipperId,
      companyName: inputShipperName,
      price: inputShipperPrice,
      service: inputShipperService,
    };

    let accessToken = localStorage.getItem("jwtToken");
    let postShipper = new XMLHttpRequest();
    postShipper.open("POST", `${url}`);
    postShipper.setRequestHeader(
      "Content-Type",
      "application/json;charset=UTF-8"
    );
    postShipper.setRequestHeader("Authorization", "Bearer " + accessToken);
    postShipper.send(JSON.stringify(dto));
    postShipper.onload = () => {
      if (postShipper.status === 200) {
        window.location.reload();
      } else {
        let errorResponse = JSON.parse(postShipper);
        companyNameErrorSpan.textContent = errorResponse.companyNameError;
        priceErrorSpan.textContent = errorResponse.priceError;
      }
    };
  });

  let editShipmentButton = document.querySelectorAll(".edit-shipment");

  for (let button of editShipmentButton) {
    button.addEventListener("click", () => {
      popUpUpsertShipment.classList.add("active");
      let shipperId = button.getAttribute("id");
      populatedShipper(shipperId);
    });
  }

  function populatedShipper(shipperId) {
    let accessToken = localStorage.getItem("jwtToken");
    let requestShipper = new XMLHttpRequest();
    requestShipper.open("GET", `${url}/detail?id=${shipperId}`);
    requestShipper.setRequestHeader("Authorization", "Bearer " + accessToken);
    requestShipper.send();
    requestShipper.onload = () => {
      let response = JSON.parse(requestShipper.response);

      let inputShipperId = document.querySelector(
        "#myModalUpsertShipment #shipperId"
      );
      let inputShipperName = document.querySelector(
        "#myModalUpsertShipment #shipperName"
      );
      let inputShipperPrice = document.querySelector(
        "#myModalUpsertShipment #shipperPrice"
      );
      let inputShipperService = document.querySelector(
        "#myModalUpsertShipment #shipperService"
      );

      inputShipperId.value = response.shipperId;
      inputShipperName.value = response.companyName;
      inputShipperPrice.value = response.price;
      if (response.service === true) {
        inputShipperService.checked = true;
      } else {
        inputShipperService.checked = false;
      }
    };
  }

  closePopUpUpsert.addEventListener("click", () => {
    let inputShipperId = document.querySelector(
      "#myModalUpsertShipment #shipperId"
    );
    let inputShipperName = document.querySelector(
      "#myModalUpsertShipment #shipperName"
    );
    let inputShipperPrice = document.querySelector(
      "#myModalUpsertShipment #shipperPrice"
    );
    let inputShipperService = document.querySelector(
      "#myModalUpsertShipment #shipperService"
    );

    inputShipperId.value = "";
    inputShipperName.value = "";
    inputShipperPrice.value = "";
    inputShipperService.removeAttribute("checked");
    popUpUpsertShipment.classList.remove("active");
  });
})();
