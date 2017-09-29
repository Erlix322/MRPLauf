<template>
  <div class="container">
    <!-- Projekt anlegen button im Headbereich-->
    <div class="card">
      <div class="card-title">  
        <h3>MRP-Lauf nach Algorithmus</h3>
      </div>
      <div class="card-content">
        <div>
          <hr class="inset">
        
          
          <p>Gesamtlauf: Zeigt das Ergebnis der verplanten Produktionsauftäge an</p>
		  <p>Weiter: Führt Schrittweise den Algorithmus aus</p>
          <p>Reset: Setzt den MRP Lauf zurück</p>
                    
		  
	    
	    <div class="row">
	   		 <div id="gantt_here" style='width:100%; height:400px;'></div>
             <div ><Giffler /></div>	    
	    </div>
          <button class="primary" @click="gesamtLauf">Gesamtlauf</button>
		  <button class="primary" @click="weiter">Weiter</button>
          <button class="primary" >Reset</button>
        </div>
         
        
      </div>
    </div>
  
    </div>
  
  </div>
</template>


<script>
  /*eslint-disable*/
  import 'dhtmlx-gantt';
  import 'dhtmlx-gantt/codebase/dhtmlxgantt.css';
  import Giffler from './Diagram/giffler.vue';

  import { Utils } from 'quasar'

  
  export default {
      
    data() {  
      return {  
  		
      }  
    },
  
    mounted() {	
        this.$store.dispatch('loadSchritte')
	    gantt.config.scale_unit= "month";
		gantt.config.step = 5; 
        gantt.init("gantt_here");
		gantt.render();
  
    },
  
    computed: {         
        color(){
        return this.$store.getters.getFarbe
        },
        ok(){
         return true;
        }
    },
  
    methods: {
      gesamtLauf() {       
		gantt.parse(this.$store.getters.displaySchritteverplant);
		gantt.render();
      },
      weiter() {
		 this.$store.dispatch('changeColor')
      }	  
	  
	  
    },
    components:{
    	Giffler    
    }
  
  
  
  
  
  }
</script>



