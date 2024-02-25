$(document).ready(function(){
    $('#btn-filter').on('click', ()=>{
        // console.log("click btn")
        let categoryId = $("#category").val();
        let typeFilePrice = $("#price").find(":selected").val();
        let requestBody = {
            categoryId : categoryId,
            typeFilePrice : typeFilePrice
        }
        // console.log(requestBody)

        $.ajax({
            type: 'POST',
            url: '/filter-product',
            contentType : "application/json; charset=UTF-8",
             data: JSON.stringify(requestBody),
            // data: $.param(requestBody),
            dataType: 'json',
            cache: false,
            async : true,
            success: function (data, status) {
                console.log('data: ', data);
                $('#body-table').empty();
                data.forEach((item,index) =>  {
                    let nodeChild = $('<tr> </tr>')
                    nodeChild.append(`<td>${index}</td>`)
                    nodeChild.append(`<td>${item.nameCategory}</td>`)
                    nodeChild.append(`<td>${item.name}</td>`)
                    nodeChild.append(`<td>${item.price}</td>`)
                    nodeChild.append(`<td>${item.description}</td>`)
                    nodeChild.append(`<td>${item.quantity}</td>`)
                    nodeChild.append(`<td>  <img src="${item.imgUrl}" class="rounded" alt="Cinque Terre" width="304" height="236"> </td></td>`)
                    nodeChild.append(` <td> <a class="btn btn-danger" href="/delete-product?id=${item.id}"> Delete</a> </td>`)
                    $('#body-table').append(nodeChild);
                })

            }
        });
    })

    $('#btn-search').on('click',()=>{
        let nameSearch = $("#input-search").val();

        $.ajax({
            type: 'GET',
            url: '/search-product',
            contentType : "application/json; charset=UTF-8",
            // data: JSON.stringify(requestBody),
            data: $.param({search: nameSearch}),
            dataType: 'json',
            cache: false,
            async : true,
            success: function (data, status) {
                console.log('data: ', data);
                $('#body-table').empty();
                data.forEach((item,index) =>  {
                    let nodeChild = $('<tr> </tr>')
                    nodeChild.append(`<td>${index}</td>`)
                    nodeChild.append(`<td>${item.nameCategory}</td>`)
                    nodeChild.append(`<td>${item.name}</td>`)
                    nodeChild.append(`<td>${item.price}</td>`)
                    nodeChild.append(`<td>${item.description}</td>`)
                    nodeChild.append(`<td>${item.quantity}</td>`)
                    nodeChild.append(`<td>  <img src="${item.imgUrl}" class="rounded" alt="Cinque Terre" width="304" height="236"> </td></td>`)
                    nodeChild.append(` <td> <a class="btn btn-danger" href="/delete-product?id=${item.id}"> Delete</a> </td>`)
                    $('#body-table').append(nodeChild);
                })

            }
        });
    })
});

