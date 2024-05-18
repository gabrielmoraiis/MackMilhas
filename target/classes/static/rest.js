async function asyncCriarPassagem(dadosPassagem, proxsucesso, proxerro) {
    const URL = `/passagem`;
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosPassagem),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, postRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; } )
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncLerPassagem(proxsucesso, proxerro) {
    const URL = `/passagem`;
    fetch(URL)
      .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta;})
      .then(resposta => resposta.json())
      .then(jsonresponse => proxsucesso(jsonresponse))
      .catch(proxerro);
}

async function asyncLerPassagemById(id, proxsucesso, proxerro) {
    const URL = `/passagem/${id}`;
    fetch(URL)
      .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta;})
      .then(resposta => resposta.json())
      .then(jsonresponse => proxsucesso(jsonresponse))
      .catch(proxerro);
}

async function asyncAlterarPassagem(dadosPassagem, proxsucesso, proxerro) {
    const URL = `/passagem/${dadosPassagem.id_passagem}`;
    const putRequest = {
        method: 'PUT',
        headers: { 'Content-type': 'application/json' },
        body: JSON.stringify(dadosPassagem),
    };
    fetch(URL, putRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; } )
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncApagarPassagem(id, proxsucesso, proxerro) {
    const URL = `/passagem/${id}`;
    const deleteRequest = {
        method: 'DELETE'
    };
    fetch(URL, deleteRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; } )
        .then(resposta => proxsucesso())
        .catch(proxerro);
}
let data;
async function asyncFazerLogin(nome, senha) {
    const URL = `/login`;
    const body = JSON.stringify({
        nome: nome,
        senha: senha
    });
    const headers = {
        'Content-type': 'application/json'
    }
    try {
        const response = await fetch(URL, { method: 'POST', body: body, headers: headers });
        data = await response.json();

        console.log(data);
        return data, true;
    } catch (error) {
        console.error("erro ao fazer requisição", error);
        return false;
    }
}
async function asyncFazerReserva(idPassagem, successHandler, errorHandler) {
    const URL = `/reserva`;
    const dadosReserva = {
        'pessoaId': data.id_pessoa, 
        'passagemId': idPassagem
    };
    console.log('Enviando dados para o servidor:', dadosReserva);

    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosReserva),
        headers: { 'Content-type': 'application/json' }
    };

    fetch(URL, postRequest)
        .then(resposta => {
            console.log('Resposta recebida:', resposta);
            if (!resposta.ok) throw Error(resposta.status);
            return resposta.json();
        })
        .then(jsonResponse => {
            console.log('Resposta JSON:', jsonResponse);
            successHandler();
        })
        .catch(error => {
            console.error('Erro durante o fetch:', error);
            errorHandler(error);
        });
}