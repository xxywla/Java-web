function delFruit(fid) {
    if (confirm("是否删除该水果")) {
        window.location.href = 'fruit.do?fid=' + fid + '&operator=del';
    }
}

function page(pageNum) {
    window.location.href = 'fruit.do?pageNum=' + pageNum;
}