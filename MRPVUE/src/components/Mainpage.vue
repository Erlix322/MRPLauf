<template>
  <div>
    <div class="layout-padding">
      <div class="card">
  
        <div class="card-title bg-secondary text-white">
          <h1>MRP-Lauf</h1>
          <p>© By HTW - Dresden 2017</p>
        </div>
  
        <div class="card-content card-force-top-padding">
          Das Projekt wurde durch Prof. Munkelt initiiert und wird seitdem durch Studenten gepflegt.
		 </br></br>
          Zur Projektseite geht es hier entlang:  <a href="https://about.gitlab.com/"><button class="secondary">GIT</button></a> 
        </div>
     
       
      </div>

       <div class="card">
  
          <div class="card-title bg-secondary text-white">
            <h1>News</h1>       
          </div>
    
          <div class="card-content card-force-top-padding">
           </br></br>
            Die neue Version enthält einen Giffler-Thompson-Algorithmus. 
          </div>
      </div>
    </div>
  </div>
</template>

<script>
  /*eslint-disable*/
  export default {
    data() {
      return {}
    },
    methods:{
      projects(){
        this.$router.push('/ProjektAnlegen')
      }
    }
  }
</script>

<style>
  
</style>
     