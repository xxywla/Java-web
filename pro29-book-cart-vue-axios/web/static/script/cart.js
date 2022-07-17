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
                    vue.cart = value.data
                }).catch(function (reason) {

                })
            },
            editBuyCount: function (cartItemId, buyCount) {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "editBuyCount",
                        cartItemId: cartItemId,
                        buyCount: buyCount
                    }
                }).then(function (value) {
                    vue.getCart()
                }).catch(function (reason) {

                })
            }
        },
        mounted: function () {
            this.getCart()
        }
    })
}