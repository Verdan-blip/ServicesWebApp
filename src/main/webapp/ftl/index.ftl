<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body>

<div class="card-body p-md-5">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-9 col-lg-7 col-xl-6">

            <h1>Записаться к мастеру</h1>

            <form class="px-md-2" action="create" method="post">

                <div class="form-group">
                    <label for="phone">Введите номер телефона:</label>
                    <input class="form-control" id="phone" type="tel" name="phone" required>
                </div>

                <div class="form-group">
                    <label for="choose-a-master">Выберите мастера:</label>
                    <select class="form-control" name="master-id" id="choose-a-master" required>
                        <option disabled selected value> -- Выберите -- </option>
                        <#list masters as master>
                            <option id="${master.getId()}" value="${master.getId()}">
                                ${master.getName()} ${master.getLastname()}
                            </option>
                        </#list>
                    </select>
                </div>

                <div class="form-group">
                    <label for="choose-a-service">Выберите услугу:</label>
                    <select class="form-control" name="service-id" id="choose-a-service" required>
                        <option disabled selected value> -- Выберите -- </option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="choose-a-date">Выберите день для записи</label>
                    <input class="form-control" id="choose-a-date" name="appointment-date" type="date" required/>
                </div>

                <div class="form-group">
                    <label for="choose-a-time">Выберите время записи</label>
                    <select class="form-control" name="appointment-time" id="choose-a-time" required>
                        <option disabled selected value> -- Выберите -- </option>
                        <option value="8:00">8:00</option>
                        <option value="9:00">9:00</option>
                        <option value="10:00">10:00</option>
                        <option value="11:00">11:00</option>
                        <option value="13:00">13:00</option>
                        <option value="14:00">14:00</option>
                        <option value="15:00">15:00</option>
                    </select>
                </div>

                <span id="appointment-status"></span>

                <br>

                <div class="form-outline mb-4">
                    <div class="col">
                        <input class="btn btn-primary" type="submit" id="submit-button" value="Записаться">
                    </div>
                </div>

            </form>

        </div>
    </div>

</div>

<div class="container">
    <div class="row">
        <div class="col-sm">
            <h1>О нас</h1>
            <iframe width="560" height="315" src="https://www.youtube.com/embed/ual_EGWuvZs?si=6CbQ9kHCvivQfx-z" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

            <div style="position:relative;overflow:hidden;">
                <a href="https://yandex.ru/maps/43/kazan/?utm_medium=mapframe&utm_source=maps"
                   style="color:#eee;font-size:12px;position:absolute;top:0px;">Казань</a>
                <a href="https://yandex.ru/maps/43/kazan/house/territoriya_derevnya_universiady_18/YEAYdw9hTkIDQFtvfXt1c3RrYA==/?ll=49.181773%2C55.742884&utm_medium=mapframe&utm_source=maps&z=17.14" style="color:#eee;font-size:12px;position:absolute;top:14px;">Территория Деревня Универсиады, 18 — Яндекс Карты</a>
                <iframe src="https://yandex.ru/map-widget/v1/?ll=49.181773%2C55.742884&mode=search&ol=geo&ouri=ymapsbm1%3A%2F%2Fgeo%3Fdata%3DCgg1NjI1NzA1MxKmAdCg0L7RgdGB0LjRjywg0KDQtdGB0L_Rg9Cx0LvQuNC60LAg0KLQsNGC0LDRgNGB0YLQsNC9LCDQmtCw0LfQsNC90YwsINCf0YDQuNCy0L7Qu9C20YHQutC40Lkg0YDQsNC50L7QvSwg0YLQtdGA0YDQuNGC0L7RgNC40Y8g0JTQtdGA0LXQstC90Y8g0KPQvdC40LLQtdGA0YHQuNCw0LTRiywgMTgiCg0iukRCFbb4XkI%2C&z=17.14" width="560" height="400" frameborder="1" allowfullscreen="true" style="position:relative;">
                </iframe>
            </div>
        </div>
    </div>
</div>

<script>
    let selectMaster = $('#choose-a-master')
    let selectService = $('#choose-a-service')
    selectMaster.change(function () {
        $.ajax({
            type: 'POST',
            url: 'find',
            dataType: 'html',
            data: {
                master_id: selectMaster.val()
            },
            success: function (response) {
                selectService.html(response)
            },
            error: function () {
                alert("Error")
            }
        })
    })
</script>

<script>
    $('#choose-a-time').change(function() {
        $.ajax({
            type: 'POST',
            url: 'check',
            data: {
                phone: $('#phone').val(),
                master_id: $('#choose-a-master').val(),
                service_id: $('#choose-a-service').val(),
                appointment_date: $('#choose-a-date').val(),
                appointment_time: $('#choose-a-time').val()
            },
            success: function (response) {
                if (response == 'Date is available') {
                    $('#submit-button').attr("disabled", false)
                } else {
                    $('#submit-button').attr("disabled", true)
                }
                $('#appointment-status').text(response)
            },
        })
    })
</script>

</body>
</html>