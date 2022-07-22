<template>
  <div id="page-cadastro" class="secao_dados_container">
    <form  @submit="salvar" id="form_cadastro">

      <!-- DADOS PESSOAIS -->
      <div id="dados_pessoais" class="secao_dados">
        <h3 class="titulo_secao">DADOS PESSOAIS</h3>
        <div class="container_input_principal">
          <label>Nome</label>
          <input type="text" placeholder="Nome" v-model= "pessoa.nome">
        </div>
        <div class="container_input_fone_profissao">
          <div id="container_input_email">
            <label>Email</label>
            <input type="email" placeholder="Email" v-model= "pessoa.email">
          </div>
          <div id="container_input_profissao">
            <label>CPF</label>
            <input type="text" placeholder="CPF" v-model= "pessoa.cpf">
          </div>
          <div id="container_input_renda">
            <label>Nascimeto</label>
            <input type="date" placeholder="Nascimeto" v-model= "pessoa.dataNascimento">
          </div>          
        </div>
        <div id="container_fones">
          <div>
            <label>Celular</label>
            <input type="text" placeholder="Celular" v-model= "pessoa.telefones.celular">
          </div>
          <div>
            <label>Telefone Fixo</label>
            <input type="tel" placeholder="Telefone Fixo" v-model= "pessoa.telefones.telefoneFixo">
          </div>
        </div>
      </div>

      <!-- PROFISSAO -->
      <div id="dados_pessoais" class="secao_dados">
        <h3 class="titulo_secao">DADOS PROFISSIONAIS</h3>        
        <div class="container_input_fone_profissao">
          <div id="container_input_email">
            <label>Profissão</label>
            <input type="text" placeholder="Profissão" v-model= "pessoa.profissao.nomeProfissao">
          </div>
          <div id="container_input_profissao">
            <label>Tipo de Contrato</label>
            <select class="combo_box" v-model= "pessoa.profissao.clt">
              <option value="">Escolha o tipo de contrato</option>
              <option value="true">CLT</option>
              <option value="false">PJ</option>
            </select>
          </div>
          <div id="container_input_renda">
            <label>Renda</label>
            <input type="text" placeholder="Renda" v-model= "pessoa.profissao.rendaMensal">
          </div>
        </div>
      </div>

      <!--ENDERECO-->
      <div id="dados_pessoais" class="secao_dados">
        <h3 class="titulo_secao">DADOS DE ENDEREÇO</h3>
        <label>CEP </label>
        <input type="text" placeholder="CEP" v-model= "pessoa.enderecos.cep" @blur="busca_endereco">
        <div class="container_input_principal">
          <label>Logradouro</label>
          <input type="text" placeholder="Logradouro" v-model= "pessoa.enderecos.logradouro">
        </div>
        <div class="container_input_fone_profissao">
          <div id="container_input_email">
            <label>Bairro</label>
            <input type="text" placeholder="Bairro" v-model= "pessoa.enderecos.bairro">
          </div>
          <div id="container_input_profissao">
            <label>Número Residência</label>
            <input type="text" placeholder="Número Residência" v-model= "pessoa.enderecos.numeroResidencia">
          </div>
          <div id="container_input_renda">
            <label>Complemento</label>
            <input type="text" placeholder="Complemento" v-model= "pessoa.enderecos.complemento">
          </div>          
        </div>
        <div id="container_fones">
          <div>
            <label>Município</label>
            <input type="text" placeholder="Município" v-model= "pessoa.enderecos.cidade">
          </div>
          <div>
            <label>Estado</label>
            <input type="text" placeholder="Estado" v-model= "pessoa.enderecos.uf">
          </div>
        </div>
      </div> 
      <button id="btn_salvar" >Salvar</button>
    </form>
  </div>
</template>

<script>
/* eslint-disable */
  import Pessoa from '../services/pessoas'
  export default {
    name: 'CadastraPessoa',

    data(){
      return {
        pessoa:{
          nome: '',
          cpf: '',
          dataNascimento: '',
          email: '',
          telefones: {
            celular: '',
            telefoneFixo: '',
          },
          profissao: {
            nomeProfissao: '',
            clt: '',
            rendaMensal: ''
          },
          enderecos: [{
            cep: '',
            numeroResidencia: '',
            complemento: '',
            logradouro: '',
            bairro: '',
            cidade: '',
            uf: ''
          }]
        },
        pessoas: []
      }
    },
    methods: {
      async salvar(e) {
        e.preventDefault();
        const data = {
          nome: this.pessoa.nome,
          cpf: this.pessoa.cpf,
          dataNascimento: this.pessoa.dataNascimento,
          email: this.pessoa.email,
          telefones: {
            celular: this.pessoa.telefones.celular,
            telefoneFixo: this.pessoa.telefones.telefoneFixo,
          },
          profissao: {
            nomeProfissao: this.pessoa.profissao.nomeProfissao,
            clt: this.pessoa.profissao.clt,
            rendaMensal: this.pessoa.profissao.rendaMensal
          },
          enderecos: [{
            cep: this.pessoa.enderecos.cep,
            numeroResidencia: this.pessoa.enderecos.numeroResidencia,
            complemento: this.pessoa.enderecos.complemento,
            logradouro: this.pessoa.enderecos.logradouro,
            bairro: this.pessoa.enderecos.bairro,
            cidade: this.pessoa.enderecos.cidade,
            uf: this.pessoa.enderecos.uf
          }]
        }
        const dataJson = JSON.stringify(data);
        const req = await fetch('http://localhost:8090/apiPessoa/cadastraPessoa', {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: dataJson          
        });
        const res = await req.json();
      },
      async busca_endereco () {
        const request = await fetch(`https://viacep.com.br/ws/${this.pessoa.enderecos.cep}/json/`)
        const response = await request.json();
        this.pessoa.enderecos.logradouro = response.logradouro;
        this.pessoa.enderecos.bairro = response.bairro;
        this.pessoa.enderecos.cidade = response.localidade;
        this.pessoa.enderecos.uf = response.uf;
        console.log(response)
      }      
    },
    mounted() {
      Pessoa.listar().then(resposta =>{
        this.pessoas = resposta.data;
      })
    }    
  }
</script>

<style scoped>

  .titulo_secao {    
    background-color: rgb(117, 121, 126);
    color: #fff;
    margin: 30px auto;
    padding: 20px;
    border-radius: 10px;
  }

  #page-cadastro{
    font-family: Verdana, Geneva, Tahoma, sans-serif;
  }

  #form_cadastro{
    padding: 10px;
    border-radius: 5px;
    background-color: #fff;
    color: rgb(3, 47, 83);
    font-weight: bolder;
    overflow: hidden;    
  }

  #form_cadastro input{
    height: 1.4rem;
    border-radius: 5px;
    border: none;
    padding: 5px;
    font-size: 1rem;    
  }

  #form_cadastro select{
    height: 2.0rem;
    border-radius: 5px;
    border: none;
    padding: 5px;    
  }

  .secao_dados{
    background-color: rgb(211, 211, 206);
    padding: 15px;
    margin: 20px auto;
    border-radius: 5px;    
  }

  .container_input_principal{
    margin: 0 auto;  
    display: flex;
    flex-direction: column;
    align-content: flex-start;
    text-align: justify;
  }

  .container_input_fone_profissao{
    margin: 15px auto;  
    display: flex;  
    justify-content: space-between;
  }

  #container_fones{
    margin: 15px auto;
    display: flex;
    justify-content: space-around;
  }

  #container_fones div{
    display: flex;
    flex-direction: column;
    width: 20vw;
    text-align: left;
  }

  #container_input_email{
    display: flex;
    flex-direction: column;
    width: 46vw;
    text-align: justify;  
  }

  #container_input_profissao{
    display: flex;
    flex-direction: column;
    width: 20vw;
    text-align: justify;
  }

  #container_input_renda{
    display: flex;
    flex-direction: column;
    width: 20vw;
    text-align: justify;
  }

  #btn_salvar{
    border: none;
    border-radius: 15px;
    background-color: rgb(65, 63, 63);
    color: #fff;
    margin: 25px auto;
    height: 40px;
    padding: 5px 25px;
    font-size: 1.2rem;
    font-weight: bold;
  }

  #btn_salvar:hover{
    cursor: pointer;
    color: #000;
    height: 40px;
    background-color: transparent;
    border: 2px solid rgb(29, 28, 28);
    transition: all linear 0.12s;
  }

  .combo_box{
    font-size: 1rem;
  }
 
</style>