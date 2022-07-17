<template>
  <div class="lista">
    <form  @submit.prevent="buscar">
        <!--<label>Nome</label>
        <input type="text" placeholder="Nome" v-model= "pessoa.nome">

        <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>-->
    </form>
    <table>
    <thead id="cabecalho_tabela">
      <tr>
        <th>NOME</th>
        <th>EMAIL</th>
        <th>PROFISSÃO</th>
        <th>RENDA</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="pessoa of pessoas" :key="pessoa.id" >
        <td id="nome_pessoa">{{ pessoa.nome }}</td>
        <td id="email_pessoa">{{ pessoa.email }}</td>
        <td id="profissao_pessoa">{{ pessoa.profissao.nomeProfissao }}</td>        
        <td id="rendaMensal_pessoa">{{ pessoa.profissao.rendaMensal }}</td>
      </tr>
    </tbody>

    </table>

  </div>
</template>

<script>
  import Pessoa from './../services/pessoas'
  export default {
    name: 'ListaComp',

    data(){
      return {
        pessoa:{
          nome: '',
          email: '',
          profissao: '',
          rendaMensal: ''

        },
        pessoas: []
      }
    },
    mounted() {
      Pessoa.listar().then(resposta =>{
        this.pessoas = resposta.data;
        console.log(resposta.data);
      })
    },
    
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

 #cabecalho_tabela tr th{
  width: 100vw;
  padding: 15px;
  border-radius: 5px;
  background-color: rgb(40, 41, 44);
  color: white;
 }

 #nome_pessoa{
  width: 50vw;
  padding: 15px;
  border-radius: 5px;
  text-align: left;
  background-color: rgb(146, 151, 155);
 }
 
 #email_pessoa{
  width: 20vw;
  padding: 15px;
  border-radius: 5px;
  text-align: left;
  background-color: rgb(189, 193, 197);
 }

 #profissao_pessoa{
  width: 20vw;
  padding: 15px;
  border-radius: 5px;
  text-align: left;
  background-color: rgb(143, 169, 196);
 }
 
 #rendaMensal_pessoa{
  width: 10vw;
  padding: 15px;
  border-radius: 5px;
  text-align: left;
  background-color: rgb(44, 54, 65);
  color: white;
 }

</style>