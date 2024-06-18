$(document).ready(ticket_showList(0))

function ticket_showList1(page) {
    $("h2").text("Danh sách vé")
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/tickets?page=${page}`,
        //xử lý khi thành công
        success: function (data) {
            console.log(data)
            // hiển thị danh sách ở đây
            let content =
                `<div>
                    <form>
                        <input id="search" type="text" class="" placeholder="keywords" style="width: 150px;height: 30px;display: inline-block">
                            <span onclick="ticket_showSearch(0)" style="width: 60px;height: 30px;display: inline-block"><i class="fa fa-search"></i></span>
                    </form>
                </div>` +
                ' <table id="list" border="1"><tr>\n' +
                ' <th>STT</td>\n' +
                ' <th>Người đặt</td>\n' +
                ' <th>Ngày đặt</td>\n' +
                ' <th>Tên phim</td>\n' +
                ' <th>Phòng</td>\n' +
                ' <th>Thời gian chiếu</td>\n' +
                ' <th>Số lượng vé</td>\n' +
                ' <th>Tổng tiền</td>\n' +
                ' </tr>';
            for (let i = 0; i < data.content.length; i++) {
                content += `<tr>
                <td>${i + 1}</td>
                <td>${data.content[i].user.name}</td>
                <td>${data.content[i].booking_day}</td>
                <td>${data.content[i].movieSchedule.movie.nameMovie}</td>
                <td>${data.content[i].movieSchedule.room.nameRoom}</td>
                <td>${data.content[i].movieSchedule.timeSlot}</td>
                <td>${data.content[i].quantityTicket}</td>
                <td>${data.content[i].quantityTicket * data.content[i].movieSchedule.room.priceRoom}</td>
                </tr>`
            }
            content += `</table>`
            $("#1-list").html(content);
            let paging =
                `<a class="rounded" onclick="ticket_showList(${data.number - 1})">&laquo;</a>`
            for (let i = 1; i <= data.totalPages; i++) {
                if (data.number == (i - 1)) paging += `<a class="active rounded" onclick="ticket_showList(${i - 1})">${i}</a>`;
                else paging += `<a class="rounded" onclick="ticket_showList(${i - 1})">${i}</a>`;
            }
            paging += `<a class="rounded" onclick="ticket_showList(${data.number + 1})">&raquo;</a>`
            $("#paging").html(paging);
        }
    })
    $("#2-create_form").hide();
    $("#3-create_form").hide();
    $("#4-create_form").hide();
}

function ticket_showList(page) {
    let keySearch = $("#search").val();
    if (keySearch === undefined || keySearch === "") {
        keySearch = "";
        $("h2").text("Danh sách vé")
    } else $("h2").text("Danh sách tìm kiếm")
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/tickets/search?page=${page}&s=${keySearch}`,
        //xử lý khi thành công
        success: function (data) {
            console.log(data)
            // hiển thị danh sách ở đây
            let content =
                `<div style="">
                    <form>`
            content += `<input id="search" type="text" class="" placeholder="keywords" style="width: 150px;height: 30px;display: inline-block" value="${keySearch}">`;
            content += `<span onclick="ticket_showList(0)" style="width: 60px;height: 30px;display: inline-block"><i class="fa fa-search p-3" style="font-size:24px"></i></span>
                    </form>
                </div>` +
                ' <table id="list" border="1"><tr>\n' +
                ' <th>STT</td>\n' +
                ' <th>Người đặt</td>\n' +
                ' <th>Ngày đặt</td>\n' +
                ' <th>Tên phim</td>\n' +
                ' <th>Phòng</td>\n' +
                ' <th>Khung giờ chiếu</td>\n' +
                ' <th>Số lượng vé</td>\n' +
                ' <th>Tổng tiền</td>\n' +
                ' </tr>';
            for (let i = 0; i < data.content.length; i++) {
                content += `<tr>
                <td>${i + 1}</td>
                <td>${data.content[i].user.name}</td>
                <td>${data.content[i].booking_day}</td>
                <td>${data.content[i].movieSchedule.movie.nameMovie}</td>
                <td>${data.content[i].movieSchedule.room.nameRoom}</td>
                <td>${data.content[i].movieSchedule.timeStart.slice(0,2) + 'h - ' + data.content[i].movieSchedule.timeStop.slice(0,2)+'h'}</td>
                <td>${data.content[i].quantityTicket}</td>
                <td>${data.content[i].quantityTicket * data.content[i].movieSchedule.room.priceRoom}</td>
                </tr>`
            }
            if (data.content.length === 0) alert("Không tìm thấy gì cả!")
            content += `</table>`
            $("#1-list").html(content);
            let paging =
                `<a class="rounded" onclick="ticket_showList(${data.number - 1})">&laquo;</a>`
            for (let i = 1; i <= data.totalPages; i++) {
                if (data.number == (i - 1)) paging += `<a class="active rounded" onclick="ticket_showList(${i - 1})">${i}</a>`;
                else paging += `<a class="rounded" onclick="ticket_showList(${i - 1})">${i}</a>`;
            }
            paging += `<a class="rounded" onclick="ticket_showList(${data.number + 1})">&raquo;</a>`
            $("#paging").html(paging);
        }
    })
    $("#2-create_form").hide();
    $("#3-create_form").hide();
    $("#4-create_form").hide();
}

function movie_showAdd() {
    $("#2-create_form").show();
    $(":text").val("");
    $("H2").text("Thêm mới phim");
    $("#1-list").empty();
    $("#paging").empty();
    $("#2-add").show();
    $("#2-save").hide();
    $(".message").text("");
}

function movie_add() {
    //lấy dữ liệu từ form html
    let nameMovie = $("#nameMovie").val();
    let producer = $("#producer").val();
    let time = $("#time").val();
    let typeMovie = $("#typeMovie").val();
    let image = $("#image").val();
    let description = $("#description").val();

    // gọi phương thức ajax
    $.ajax({
        // headers: {
        //     'accept': 'application/json',
        //     'content-Type': 'application/json'
        // },
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            nameMovie: nameMovie,
            producer: producer,
            time: time,
            typeMovie: typeMovie,
            image: image,
            description: description
        }),
        //tên API
        url: "http://localhost:8080/api/movies",
        //xử lý khi thành công
        success: $(".message").text("Thêm mới thành công")
    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function movie_showList() {
    $("#paging").empty();
    $("#2-create_form").hide();
    $("#3-create_form").hide();
    $("#4-create_form").hide();
    $("h2").text("Danh sách phim")
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/api/movies",
        //xử lý khi thành công
        success: function (data) {
            console.log(data)
            // hiển thị danh sách ở đây
            let content =
                '<input type="button" onclick="movie_showAdd()" class="btn btn-link fas" value="Thêm mới +" style="margin-left: 88.6%;margin-top: 0;color: #0d6efd;border-color: #0d6efd">' +
                ' <table id="list" border="1"><tr>\n' +
                ' <th>STT</td>\n' +
                ' <th>Tên phim</td>\n' +
                ' <th>Nhà sản xuất</td>\n' +
                ' <th>Thời lượng</td>\n' +
                ' <th>Thể loại</td>\n' +
                ' <th>Hình ảnh</td>\n' +
                ' <th>Mô tả</td>\n' +
                ' <th>Chỉnh sửa</td>\n' +
                ' <th>Xóa</td>\n' +
                ' </tr>';
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                <td >${i + 1}</td>
                <td >${data[i].nameMovie}</td>
                <td >${data[i].producer}</td>
                <td >${data[i].time}</td>
                <td >${data[i].typeMovie}</td>
                <td><img src="${data[i].image}" alt="..." style="width: 60px;height: 80px"/></td>
                <td >${data[i].description}</td>
                <td ><input type="button" onclick="movie_showUpdate(${data[i].id})" class="btn btn-link" value="Sửa" style="color: orange"></td>
                <td ><input type="button" onclick="movie_delete(${data[i].id})" class="btn btn-link" value="Xóa" style="color: red"></td>
                </tr>`
            }
            content += "</table>"
            $("#1-list").html(content);
        }
    })
}

function movie_showUpdate(id) {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/movies/${id}`,
        success: function (data) {
            movie_showAdd();
            $("H2").text("Chỉnh sửa phim");
            $("#id").val(`${data.id}`);
            $("#nameMovie").val(`${data.nameMovie}`);
            $("#producer").val(`${data.producer}`);
            $("#time").val(`${data.time}`);
            $("#typeMovie").val(`${data.typeMovie}`);
            $("#image").val(`${data.image}`);
            $("#description").val(`${data.description}`);
            $("#2-add").hide()
            $("#2-save").show().html(`<input type=\"submit\" value=\"Lưu\" onclick=\"movie_update(${data.id})\">`);
            // $("#tr").html("<td><label >ID:</label></td>\n" + `<td><input type="text" id='id' value="${data.id}" readonly></td>`);
        }
    });
}

function movie_update(id) {
    //lấy dữ liệu từ form html
    let nameMovie = $("#nameMovie").val();
    let producer = $("#producer").val();
    let time = $("#time").val();
    let typeMovie = $("#typeMovie").val();
    let image = $("#image").val();
    let description = $("#description").val();
    // gọi phương thức ajax
    $.ajax({
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            id: id,
            nameMovie: nameMovie,
            producer: producer,
            time: time,
            typeMovie: typeMovie,
            image: image,
            description: description
        }),
        //tên API
        url: `http://localhost:8080/api/movies/${id}`,
        //xử lý khi thành công
        success: function () {
            $(".message").text("Cập nhật thành công")
            console.log("Cập nhật thành công");
        }
    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function movie_delete(id) {
    $.getJSON("/api/movies/" + id, function (result) {
        if (confirm("Bạn có chắc chắn muốn xóa phim '" + result.nameMovie + "' không?")) {
            $.ajax({
                type: "DELETE",
                //tên API
                url: `http://localhost:8080/api/movies/${id}`,
                //xử lý khi thành công
                success: movie_showList
            });
        } else console.log("Hủy bỏ thao tác xoá!")
    })
}

function room_showAdd() {
    $("#3-create_form").show();
    $(":text").val("");
    $("H2").text("Thêm mới phòng");
    $("#1-list").empty();
    $("#paging").empty();
    $("#3-add").show();
    $("#3-save").hide();
    $(".message").text("");
}

function room_add() {
    //lấy dữ liệu từ form html
    let nameRoom = $("#nameRoom").val();
    let quantitySeat = $("#quantitySeat").val();
    let typeRoom = $("#typeRoom").val();
    let priceRoom = $("#priceRoom").val();

    // gọi phương thức ajax
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            nameRoom: nameRoom,
            quantitySeat: quantitySeat,
            typeRoom: typeRoom,
            priceRoom: priceRoom
        }),
        //tên API
        url: "http://localhost:8080/api/rooms",
        //xử lý khi thành công
        success: function () {
            $(".message").text("Thêm mới thành công")
            console.log("Thêm mới thành công");
        }
    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function room_showList() {
    $("#paging").empty();
    $("#2-create_form").hide();
    $("#3-create_form").hide();
    $("#4-create_form").hide();
    $("h2").text("Danh sách phòng")
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/api/rooms",
        //xử lý khi thành công
        success: function (data) {
            console.log(data)
            // hiển thị danh sách ở đây
            let content =
                '<input type="button" onclick="room_showAdd()" class="btn btn-link fas" value="Thêm mới +" style="margin-left: 88.6%;margin-top: 0;color: #0d6efd;border-color: #0d6efd">' +
                ' <table id="list" border="1"><tr>\n' +
                ' <th>STT</td>\n' +
                ' <th>Tên phòng</td>\n' +
                ' <th>Tổng số lượng ghế</td>\n' +
                ' <th>Loại phòng</td>\n' +
                ' <th>Giá</td>\n' +
                ' <th>Chỉnh sửa</td>\n' +
                ' <th>Xóa</td>\n' +
                ' </tr>';
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                <td >${i + 1}</td>
                <td >${data[i].nameRoom}</td>
                <td >${data[i].quantitySeat}</td>
                <td >${data[i].typeRoom}</td>
                <td >${data[i].priceRoom}</td>         
                <td ><input type="button" onclick="room_showUpdate(${data[i].id})" class="btn btn-link" value="Sửa" style="color: orange"></td>
                <td ><input type="button" onclick="room_delete(${data[i].id})" class="btn btn-link" value="Xóa" style="color: red"></td>
                </tr>`
            }
            content += "</table>"
            $("#1-list").html(content);
        }
    })
}

function room_showUpdate(id) {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/rooms/${id}`,
        success: function (data) {
            room_showAdd();
            $("H2").text("Chỉnh sửa phòng");
            $("#id3").val(`${data.id}`);
            $("#nameRoom").val(`${data.nameRoom}`);
            $("#quantitySeat").val(`${data.quantitySeat}`);
            $("#typeRoom").val(`${data.typeRoom}`);
            $("#priceRoom").val(`${data.priceRoom}`);
            $("#3-add").hide()
            $("#3-save").show().html(`<input type=\"submit\" value=\"Lưu\" onclick=\"room_update(${data.id})\">`);
        }
    });
}

function room_update(id) {
    //lấy dữ liệu từ form html
    let nameRoom = $("#nameRoom").val();
    let quantitySeat = $("#quantitySeat").val();
    let typeRoom = $("#typeRoom").val();
    let priceRoom = $("#priceRoom").val();
    // gọi phương thức ajax
    $.ajax({
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            id: id,
            nameRoom: nameRoom,
            quantitySeat: quantitySeat,
            typeRoom: typeRoom,
            priceRoom: priceRoom,
        }),
        //tên API
        url: `http://localhost:8080/api/rooms/${id}`,
        //xử lý khi thành công
        success: function () {
            $(".message").text("Cập nhật thành công")
            console.log("Cập nhật thành công");
        }
    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function room_delete(id) {
    $.getJSON("/api/rooms/" + id, function (result) {
        if (confirm("Bạn có chắc chắn muốn xóa phòng '" + result.nameRoom + "' không?")) {
            $.ajax({
                type: "DELETE",
                //tên API
                url: `http://localhost:8080/api/rooms/${id}`,
                //xử lý khi thành công
                success: room_showList
            });
        } else console.log("Hủy bỏ thao tác xoá!")
    })
}


function schedule_showList() {
    $("#paging").empty();
    $("#2-create_form").hide();
    $("#3-create_form").hide();
    $("#4-create_form").hide();
    $("h2").text("Lịch chiếu phim")
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/api/schedules",
        //xử lý khi thành công
        success: function (data) {
            console.log(data)
            // hiển thị danh sách ở đây
            let content =
                '<input type="button" onclick="schedule_showAdd()" class="btn btn-link fas" value="Thêm mới +" style="margin-left: 88.6%;margin-top: 0;color: #0d6efd;border-color: #0d6efd">' +
                ' <table id="list" border="1"><tr>\n' +
                ' <th>STT</td>\n' +
                ' <th>Phim</td>\n' +
                ' <th>Ngày chiếu</td>\n' +
                ' <th>Khung giờ</td>\n' +
                ' <th>Số lượng ghế trống</td>\n' +
                ' <th>Phòng</td>\n' +
                ' <th>Chỉnh sửa</td>\n' +
                ' <th>Xóa</td>\n' +
                ' </tr>';
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                <td >${i + 1}</td>
                <td >${data[i].movie.nameMovie}</td>  
                <td >${data[i].screeningDay}</td>  
                <td >${data[i].timeStart.slice(0,2) + 'h - ' + data[i].timeStop.slice(0,2)+'h'}</td>
                <td >${data[i].emptySeat}</td>
                <td >${data[i].room.typeRoom}</td>  
                <td ><input type="button" onclick="schedule_showUpdate(${data[i].id})" class="btn btn-link" value="Sửa" style="color: orange"></td>
                <td ><input type="button" onclick="schedule_delete(${data[i].id})" class="btn btn-link" value="Xóa" style="color: red"></td>
                </tr>`
            }
            content += "</table>"
            $("#1-list").html(content);
        }
    })
}

function schedule_showUpdate(id) {
    $("#paging").empty();
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/schedules/${id}`,
        success: function (data) {
            console.log(data)
            $("H2").text("Sửa lịch chiếu phim");
            $("#1-list").empty();

            let content = `<table >
            <tr>
                <td><input type="hidden" id="id4" value="${data.id}" ></td>
            </tr>
            <tr>
                <td><label for="timeStart">Giờ bắt đầu</label></td>
                <td>
                <input type = "text" class= "timepicker" value="${data.timeStart}" id = "timeStart" onchange="checkTime()"> 
                </td>
            </tr>
            <tr>
                <td><label for="timeStop">Giờ kết thúc</label></td>
                <td>
            <input type = "text" class= "timepicker" id = "timeStop" value="${data.timeStop}" onchange="checkTime()"> 
                </td>
            </tr>
            <tr>
                <td><label for="screeningDay">Ngày chiếu</label></td>
                <td>
                <input type = "text" id = "screeningDay" onchange="hiddenTime()"> 
                </td>
            </tr>
            <tr>
                <td><label>Phòng</label></td>`;
            $.ajax({
                type: "GET",
                //tên API
                url: "http://localhost:8080/api/rooms",
                //xử lý khi thành công
                success: function (dataRoom) {
                    console.log(dataRoom)
                    content += `
                    <td>
                    <select id="room" onchange="hiddenTime()">`;
                    for (let i = 0; i < dataRoom.length; i++) {
                        if (data.room.nameRoom === `${dataRoom[i].nameRoom}`) {
                            content += `<option value="${dataRoom[i].id}" selected>${dataRoom[i].nameRoom}</option>`;
                        } else content += `<option value="${dataRoom[i].id}">${dataRoom[i].nameRoom}</option>`;
                    }
                    content += `
                    </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="emptySeat">Số ghế trống</label></td>
                    <td><input type="text" id="emptySeat" value="${data.emptySeat}"></td>
                </tr>
                <tr>
                    <td><label>Phim</label></td>`;
                    $.ajax({
                        type: "GET",
                        //tên API
                        url: "http://localhost:8080/api/movies",
                        //xử lý khi thành công
                        success: function (dataMovie) {
                            console.log(dataMovie)
                            content += `<td>
                            <select id="movie">`;
                            for (let i = 0; i < dataMovie.length; i++) {
                                if (data.movie.nameMovie === `${dataMovie[i].nameMovie}`) {
                                    content += `<option value="${dataMovie[i].id}" selected>${dataMovie[i].nameMovie}</option>`;
                                } else content += `<option value="${dataMovie[i].id}">${dataMovie[i].nameMovie}</option>`;
                            }
                            content += `
                            </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center"><input type=\"submit\" value=\"Lưu\" onclick=\"schedule_update(${data.id})\"></span></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center"><span class="message"></span></td>
                        </tr>
                        </table>`;
                            $("#4-create_form").show().html(content);
                                // $.ajax({
                                //     type: "GET",
                                //     url: `http://localhost:8080/api/schedules/${data.room.id}/${data.screeningDay}`,
                                //     //xử lý khi thành công
                                //     success: function (data2) {
                                //         let myArray = [];
                                //         if (data2.length !== 0) {
                                //             data2.forEach(item => {
                                //                 console.log(item.timeStart, data.timeStart)
                                //                 if (item.timeStart != data.timeStart) {
                                //                     let stop = item.timeStop.slice(0, 4) + "1";
                                //                     let value = [item.timeStart, stop]
                                //                     myArray.push(value)
                                //                 }
                                //             })
                                //         }
                                //         reloadTimepicker(myArray)
                                //     }
                                // });
                                setupUpdate(`${data.room.id}`,`${data.screeningDay}`,`${data.timeStart}`)
                            // hiddenTime()
                            $("#screeningDay").datepicker({dateFormat: "yy-mm-dd"});
                            $("#screeningDay").datepicker("setDate",`${data.screeningDay}`);
                        }
                    });
                }
            });
        }

    });
}

function setupUpdate(id,screeningDay,timeStart) {
        $.ajax({
            type: "GET",
            url: `http://localhost:8080/api/schedules/${id}/${screeningDay}`,
            //xử lý khi thành công
            success: function (data2) {
                let myArray = [];
                if (data2.length !== 0) {
                    data2.forEach(item => {
                        // console.log(item.timeStart, data.timeStart)
                        if (item.timeStart != timeStart) {
                            let stop = item.timeStop.slice(0, 4) + "1";
                            let value = [item.timeStart, stop]
                            myArray.push(value)
                        }
                    })
                }
                reloadTimepicker(myArray)
            }
        })
            // success: (response) => {
            //     resolve(response)
            // },
            // error: function (err) {
            //     reject(err)
            // }
}

async function schedule_update(id) {
    //lấy dữ liệu từ form html
    let timeStart = $("#timeStart").val();
    let timeStop = $("#timeStop").val();
    let screeningDay = $("#screeningDay").val();
    let emptySeat = $("#emptySeat").val();
    let idRoom = $("#room").val();
    let idMovie = $("#movie").val();
    let message = $(".message").val();
    if (timeStop !== "" && timeStart !== "" && screeningDay !== "" && emptySeat !== "" && idRoom !== "" && idMovie !== "" && message ==="") {
        timeStart += ':00';
        timeStop += ':00';
    event.preventDefault()
    let room = await getRoom(idRoom);
    let movie = await getMovie(idMovie);

    let data = {
        id: id,
        timeStart: timeStart,
        timeStop: timeStop,
        screeningDay: screeningDay,
        emptySeat: emptySeat,
        room: room,
        movie: movie
    }
    $.ajax({
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        //tên API
        url: `http://localhost:8080/api/schedules/${id}`,
        //xử lý khi thành công
        success: function () {
            $(".message").text("Cập nhật lịch thành công")
            console.log("Cập nhật lịch thành công");
        }
    });
    }else $(".message").text("Vui lòng nhập đủ thông tin")
    event.preventDefault()
}

function schedule_delete(id) {
    $.getJSON("/api/schedules/" + id, function (result) {
        if (confirm("Bạn có chắc chắn muốn xóa lịch phim '" + result.movie.nameMovie + "' không?")) {
            $.ajax({
                type: "DELETE",
                //tên API
                url: `http://localhost:8080/api/schedules/${id}`,
                //xử lý khi thành công
                success: schedule_showList
            });
        } else console.log("Hủy bỏ thao tác xoá!")
    })
}

function schedule_showAdd() {
    $("H2").text("Thêm lịch chiếu phim");
    $("#1-list").empty();
    $("#paging").empty();

    let content = `<table >
    <tr>
        <td><input type="hidden" id="id4" ></td>
    </tr>
    <tr>
        <td><label for="timeStart">Giờ bắt đầu</label></td>
        <td>
        <input type="text" class="timepicker" id="timeStart" onchange="checkTime()">
        </td>
    </tr>
    <tr>
        <td><label for="timeStop">Giờ kết thúc</label></td>
        <td>
        <input type="text" class="timepicker" id="timeStop" onchange="checkTime()">
        </td>
    <tr>
        <td><label for="screeningDay"  >Ngày chiếu</label></td>
        <td>
        <input type="text" id="screeningDay" onchange="hiddenTime()">
        </td>
    </tr>
        <tr>
           <td><label>Phòng</label></td>`;
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/api/rooms",
        //xử lý khi thành công
        success: function (dataRoom) {
            console.log(dataRoom)
            content += `
            <td>
                <select id="room" onchange="hiddenTime()"><option></option>`;
            for (let i = 0; i < dataRoom.length; i++) {
                content += `<option value="${dataRoom[i].id}">${dataRoom[i].nameRoom}</option>`;
            }
            content += `
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="emptySeat">Số ghế trống</label></td>
            <td>
            <input type="text" id="emptySeat" >`
            content += `
            </td>
        </tr>
        <tr>
                <td><label>Phim</label></td>`;
            $.ajax({
                type: "GET",
                //tên API
                url: "http://localhost:8080/api/movies",
                //xử lý khi thành công
                success: function (dataMovie) {
                    console.log(dataMovie)
                    content += `<td>
                <select id="movie" ><option></option>`;
                    for (let i = 0; i < dataMovie.length; i++) {
                        content += `<option value="${dataMovie[i].id}">${dataMovie[i].nameMovie}</option>`;
                    }
                    content += `
                </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="Tạo mới" id="4-add" onclick="schedule_add()"><span id="4-save"></span></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><span class="message">&#160;</span></td>
            </tr>
            </table>`;
                    $("#4-create_form").show().html(content);
                    $('input.timepicker').timepicker({
                        timeFormat: 'G:i',
                        interval: 30,
                        minTime: '8',
                        maxTime: '23',
                        defaultTime: null,
                        startTime: '8',
                        dynamic: false,
                        dropdown: true,
                        scrollbar: true,
                        // disableTimeRanges: [
                        //     ['11:00', '15:01'],
                        //     ['16:00', '18:01']
                        // ]
                    });
                    $("#screeningDay").datepicker({
                        dateFormat:"yy-mm-dd"
                    });
                }
            });
        }
    });
}
// <select id="emptySeat"><option></option>`;
// for (let i = 0; i < dataRoom.length; i++) {
//     content += `<option value="${dataRoom[i].quantitySeat}">${dataRoom[i].quantitySeat}</option>`;
// }

async function hiddenTime() {
    let screeningDay = $("#screeningDay").val();
    let idRoom = $("#room").val();

    if (screeningDay !== "" && idRoom !== "") {
        $.ajax({
            type: "GET",
            url: `http://localhost:8080/api/schedules/${idRoom}/${screeningDay}`,
            //xử lý khi thành công
            success: function (data) {
                let myArray = [];
                if (data.length !== 0) {
                    data.forEach(item => {
                        let stop = item.timeStop.slice(0, 4) + "1";
                        let value = [item.timeStart, stop]
                        myArray.push(value)
                        $(".timepicker").val("")
                    })
                }
                    reloadTimepicker(myArray)
            }
        })
        event.preventDefault()
        let room = await getRoom(idRoom);
        $('#emptySeat').val(`${room.quantitySeat}`)
    }
    // event.preventDefault()
}

function reloadTimepicker(myArray){
    $('input.timepicker').timepicker('remove');
    $('input.timepicker').timepicker({
        timeFormat: 'G:i',
        interval: 30,
        minTime: '8',
        maxTime: '23',
        defaultTime: null,
        startTime: '8',
        dynamic: false,
        dropdown: true,
        scrollbar: true,
        disableTimeRanges:  myArray
    });
}

function checkTime(){
    let timeStop = $("#timeStop").val();
    let timeStart = $("#timeStart").val();
    if (timeStop !== "" && timeStart !== ""){
        let start = parseInt(timeStart.slice(0,-3))*60 + parseInt(timeStart.slice(-2))
        let stop = parseInt(timeStop.slice(0,-3))*60 + parseInt(timeStop.slice(-2))
        let range = stop - start;
        if (range < 90 || range >180) $(".message").text("Khoảng thời gian không phù hợp (1.5h-3h)")
        else $(".message").text("")
    }
}

async function schedule_add() {
    event.preventDefault()
    //lấy dữ liệu từ form html
    let timeStop = $("#timeStop").val();
    let timeStart = $("#timeStart").val();
    let screeningDay = $("#screeningDay").val();
    let emptySeat = $("#emptySeat").val();
    let idRoom = $("#room").val();
    let idMovie = $("#movie").val();
    let message = $(".message").val();
    if (timeStop !== "" && timeStart !== "" && screeningDay !== "" && emptySeat !== "" && idRoom !== "" && idMovie !== "" && message ==="") {
        timeStart += ':00'
        timeStop += ':00'
        let room = await getRoom(idRoom);
        let movie = await getMovie(idMovie);
        let data = {
            timeStart: timeStart,
            timeStop: timeStop,
            screeningDay: screeningDay,
            emptySeat: emptySeat,
            room: room,
            movie: movie
        }
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            //tên API
            url: `http://localhost:8080/api/schedules`,
            //xử lý khi thành công
            success: function () {
                $(".message").text("Thêm mới thành công")
                console.log("Thêm mới thành công");
                hiddenTime()
            }
        });
    }else $(".message").text("Vui lòng nhập đủ thông tin")
}

function getRoom(idRoom) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "http://localhost:8080/api/rooms/" + idRoom,
            method: "GET",
            success: (response) => {
                resolve(response)
            },
            error: function (err) {
                reject(err)
            }
        })
    })
}

function getMovie(id) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "http://localhost:8080/api/movies/" + id,
            method: "GET",
            success: (response) => {
                resolve(response)
            },
            error: function (err) {
                reject(err)
            }
        })
    })
}
