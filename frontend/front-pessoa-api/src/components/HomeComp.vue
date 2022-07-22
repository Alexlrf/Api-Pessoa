<template>
  <div class="home">
    <h1>LISTA DE PESSOAS</h1>
    <ListaPessoas />
    <CadastraPessoa />
  </div>
</template>

<script>
import ListaPessoas from './ListaPessoas.vue';
import CadastraPessoa from './CadastraPessoa.vue';
export default {
    name: "HomeComp",
    components: { ListaPessoas, CadastraPessoa }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
