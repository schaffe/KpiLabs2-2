var citiesList;

var selectedId;

var sortParams = {
    column: null,
    desc: false,
    nextOrder: function (column) {
        console.log(column);
        console.log(this.column);
        console.log(this.desc);

        if (column != this.column) {
            this.column = column;
            return this.desc = false, this.desc;
        } else {
            this.column = column;
            return this.desc = !this.desc, this.desc;
        }
    },
    keepOrder: function () {
        this.desc = !this.desc;
    }
};

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var addCity = function () {
    var $form = $('#add_city_form');

    $.ajax({
        type: "POST",
        url: $form.attr('action'),
        data: JSON.stringify($form.serializeObject()),
        success: function (city) {
            resetForm($form);
            citiesList.push(city);
            sortParams.keepOrder();
            sortByColumn(sortParams.column, true)
        },
        contentType: "application/json",
        dataType: "json"
    });
    //
    //$.post($form.attr('action'), JSON.stringify($form.serialize()))
    //    .done(function (city) {
    //        resetForm($form);
    //        citiesList.push(city);
    //        sortParams.keepOrder();
    //        sortByColumn(sortParams.column, true)
    //    })
};


var sortByColumn = function (column) {
    switch (column) {
        case 'name' :
            citiesList.sort(sortBy(column, sortParams.nextOrder(column), function (a) {
                return a.toUpperCase()
            }));
            break;
        case 'population':
            citiesList.sort(sortBy(column, !sortParams.nextOrder(column), parseInt));
            break;
    }

    clearTable();
    populateTable(citiesList);
};

var sortBy = function (field, reverse, primer) {

    var key = primer ?
        function (x) {
            return primer(x[field])
        } :
        function (x) {
            return x[field]
        };

    reverse = !reverse ? 1 : -1;

    return function (a, b) {
        return a = key(a), b = key(b), reverse * ((a > b) - (b > a));
    }
};

function showAll() {
    $.get("../rest/city/").done(function (cities) {
        citiesList = cities;
        //if(sort) {
        //    citiesList.sort_by(sort);
        //}
        populateTable(citiesList);
    });
};

function appendCity(city) {
    var row = jQuery("<tr id=\"" + city.id + "\" >" +
    "<td>" + city.name + "</td>" +
    "<td>" + city.population + "</td>" +
    "<td>" + "<a href=\"#\" class=\"edit\"><img src=\"../resources/img/edit_32.png\" width='16px'/></a></td>" +
    "<td>" + "<a href=\"#\" class=\"del\"><img src=\"../resources/img/delete.png\" width = '16px'></a></td></tr>");
    $("#table_body").append(row);
}
var populateTable = function (cities) {
    for (var i = 0; i < cities.length; i++) {
        var city = cities[i];
        appendCity(city);
    }
};

function clearTable() {
    $('#table_body').empty();
}

function resetForm($form) {
    $form.find('input:text, input:password, input:file, select, textarea').val('');
    $form.find('input:radio, input:checkbox')
        .removeAttr('checked').removeAttr('selected');
    $('#add_city_form_population').val('');
}

function changeBg(id) {
    var city = getCityFromList(id);
    var url = city.photo.url;
    $('.bg').css('background-image', 'url(' + url + ')');
}


function getCityFromList(id) {
    for (var i = 0; i < citiesList.length; i++) {
        if (citiesList[i].id == id) {
            return citiesList[i];
        }
    }
}

var selectedId;
function showPopupCity(id) {
    var popUp = document.getElementById("popupcontent");
    popUp.style.visibility = "visible";
    ;
    selectedId = id;
    var $name = $("#edit_city_form_name");
    var $population = $("#edit_city_form_population");

    var city = getCityFromList(id);

    $name.val(city.name);
    $population.val(city.population);
}

function editCity() {
    var id = selectedId;
    var $name = $("#edit_city_form_name");
    var $population = $("#edit_city_form_population");

    var name = $name.val();
    var pop = $population.val();

    var city = {id: id, name: name, population: pop};

    $.ajax({
        url: '../rest/city/' + id,
        data: JSON.stringify(city),
        contentType: "application/json",
        type: 'PUT',
        success: function () {
            hidePopup();
            clearTable();
            showAll();
        },
        error: function () {
            alert("error");
        }
    });
}

function hidePopup() {
    var popUp = document.getElementById("popupcontent");
    popUp.style.visibility = "hidden";
}


$(document).ready(function () {
    showAll();

    $('#add_city_form').submit(function (event) {
        event.preventDefault();
        addCity();
    });

    $('#edit_city_form').submit(function (event) {
        event.preventDefault();
        editCity();
    });
});

$(document).on('click', '.del', function () {
    var $elem = $(this);
    var id = $elem.parent().parent().attr('id');
    var r = confirm("Are you sure want to delete " + getCityFromList(id).name + "?");
    if (r == true) {
        function del(id) {
            $.ajax({
                url: '../rest/city/' + id,
                type: 'DELETE',
                success: function () {
                    for (var i = 0; i < citiesList.length; i++) {
                        if (citiesList[i].id == id) {
                            citiesList.splice(i, 1);
                            $elem.parent().parent().remove();
                        }
                    }
                },
                error: function () {
                    alert("error");
                }
            });
        }

        del(id);
    }
});

$(document).on('click', '.edit', function () {
    var $elem = $(this);
    var id = $elem.parent().parent().attr('id');
    showPopupCity(id);
});

$(document).on('click', 'tr', function () {
    var $elem = $(this);
    selectedId = $elem.attr('id');
    console.log(selectedId)
    changeBg(selectedId);
});

$(document).on('click', '#photo_img', function () {
    if (selectedId) {
        var url = prompt("Enter url");

        $.ajax({
            url: '../rest/city/' + selectedId + '/photo',
            data: url,
            type: 'PUT',
            contentType: 'text/plain',
            success: function (photo) {
                var city = getCityFromList(selectedId);
                city.photo = photo;
                changeBg(selectedId)
            },
            error: function () {
                alert("error");
            }
        });
    }
});


