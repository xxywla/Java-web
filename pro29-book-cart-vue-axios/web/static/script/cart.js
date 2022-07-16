function editBuyCount(cartItemId, buyCount) {
    if (buyCount > 0) {
        window.location.href = "cart.do?operate=editBuyCount&cartItemId=" + cartItemId + "&buyCount=" + buyCount;
    }
}

window.onload = function () {
    var vue = new Vue({
        el: "#cart_div",
        data: {
            cart: {}
        },
        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "getCart"
                    }
                }).then(function (value) {
                    var cart = value.data
                    vue.cart = cart
                }).catch(function (reason) {

                })
            }
        },
        mounted: function () {
            this.getCart()
        }
    })
}