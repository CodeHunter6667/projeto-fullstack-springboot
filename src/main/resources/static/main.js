function salvarUsuario() {

    let id = $("#id").val();
    let nome = $("#nome").val();
    let idade = $("#idade").val();

    $.ajax({
        method: "POST",
        url: "salvar",
        data: JSON.stringify({ id: id, nome: nome, idade: idade }),
        contentType: "application/json; charset-utf-8",
        success: function (response) {
            $("#id").val(response.id)
            alert("Salvo com Sucesso");
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao salvar: " + xhr.responseText)
    })

}

$(document).ready(function () {
    $("#resetBtn").click(function () {
        $("#formCadastroUsuario").trigger("reset");
    });
});

function pesquisaUser() {
    let nome = $("#nomeBusca").val();

    if (nome != null && nome.trim() != '') {
        $.ajax({
            method: "GET",
            url: "listanome",
            data: "nome=" + nome,
            success: function (response) {
                $('#tabelaResultados > tbody > tr').remove();
                for (let i = 0; i < response.length; i++) {
                    $('#tabelaResultados > tbody').append('<tr id="'+ response[i].id +'"><td>' + response[i].id + '</td><td>' + response[i].nome + '</td><td><button type="button" class="btn btn-warning" onclick="colocarEmEdicao(' + response[i].id + ')">Ver</button><td><button type="button" class="btn btn-danger" onclick="deleteUser(' + response[i].id + ')">Deletar</button></td></td></tr>');
                }
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao buscar usuario: " + xhr.responseText)
        })
    }
}

function colocarEmEdicao(id) {
    $.ajax({
        method: "GET",
        url: "listaporid",
        data: "id=" + id,
        success: function (response) {
            $("#id").val(response.id);
            $("#nome").val(response.nome);
            $("#idade").val(response.idade);

            $("#pesquisarModal").modal("hide");
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao buscar usuario por id: " + xhr.responseText)
    })
}

function deleteUser(id){
    $.ajax({
        method: "DELETE",
        url: "deleta",
        data: "id=" + id,
        success: function (response) {
            $('#' + id).remove();
            alert("Usuario deletado com sucesso")
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao deletar usuario: " + xhr.responseText)
    })
}