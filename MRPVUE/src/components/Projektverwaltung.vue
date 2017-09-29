<template>
  <div>
  <div class="card">
  
      <div class="card-title">  
        <h3>Projektverwaltung</h3>  
      </div>  
      <div class="card-content">  
        <div>  
          <hr class="inset">  
          <p>Wählen Sie ein Projekt das angelegt werden soll</p>            
          
           <small>Name:</small>

           <q-dialog-select
              type="radio"
              v-model="select"
              :options="selectOptions"
              ok-label="OK"
              cancel-label="Abbrechen"
              title="Radios"
            ></q-dialog-select>
          
          <button class="primary" @click="loadProject">
                     Laden
          </button>
        </div>
      </div>

      <hr class="inset">  
          <p>Hier sehen sie welche Dateistruktur erzeugt wird</p> 
          <button class="primary" >
                     Speichern
          </button>
      <!-- Ansicht des Dateibaumes -->
      <q-tree
          :model="treeModel"
          contract-html="<i>remove_circle</i>"
          expand-html="<i>add_circle</i>"
        ></q-tree>
    </div></div>
</template>

<script>
/*eslint-disable*/
export default {
  data () {
    return {      
    select: 'GESAMT',
    treeModel: [],
    selectOptions: [
        {
          label: '3dslicer',
          value: '3DSLICER'
        },
        {
          label: 'MESHLAB',
          value: 'MESHLAB'
        },
        {
          label: 'GESAMT',
          value: 'GESAMT'
        },
    ]
    }
  },
  methods:{
    loadProject(){
     var temp = this.$store.getters.getCurrentStructure
     console.log(temp)
     var obj = {
       currStr : temp,
       projectType : this.select
     }
     this.$store.dispatch("setProject", obj)
     this.treeModel = this.$store.getters.getProjectStructure     
    }   
  },
  mounted(){
    this.$store.dispatch("loadData")
  }
}
</script>

<style>
</style>
