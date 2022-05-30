function delFruit(fid) {
    if (confirm("是否删除该水果")) {
        window.location.href = 'del.do?fid=' + fid;
    }
}

function page(pageNum) {
    window.location.href = 'index?pageNum=' + pageNum;
}