/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


fetch('./ReloadShoppingCartController', {
    method: 'GET',
    headers: {
        'content-type': 'application/json'
    }
}).then(response => {
    if (!response.ok) {
        throw new Error("Network was not ok! Status: " + response.status);
    } else
        response.text();
}).then(data => {
   
}).catch(error => {
    window.location.href('404.jsp');
});