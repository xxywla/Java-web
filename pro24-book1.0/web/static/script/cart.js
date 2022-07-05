function editBuyCount(cartItemId, buyCount) {
    if (buyCount > 0) {
        window.location.href = "cart.do?operate=editBuyCount&cartItemId=" + cartItemId + "&buyCount=" + buyCount;
    }
}