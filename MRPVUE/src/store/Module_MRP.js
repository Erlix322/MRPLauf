/*eslint-disable*/
import {configFile} from '../config/configFile'

const moduleMRPStore = {

    state: {       
		Schritteverplant: {data:[]},
        AlgorithmusSchritte: [],
        Farbe:["pink","blue"],
    },
    getters: {
        displayedAuftraege: state => state.Produktionsauftrage,
        displayAlgorithmusSchritte: state => state.folders, 
        displaySchritteverplant: state => state.Schritteverplant,
        getFarbe: state => state.Farbe,
    },
    mutations: {
        LOAD_STEPS(state, steps) { state.Schritteverplant.data = steps.data; },
        CCOLOR(state){state.Farbe = ["green",""]},
    },
    actions: {
    	loadSchritte({ commit }) {
            fetch(configFile.STEPS, {
                    method: "GET",                    
                })
                .then(function(response) {
                    return response.json();
                })
                .then(function(aufs) {                  
                        commit('LOAD_STEPS', aufs)                         
                });
        },
        changeColor({commit}){
        	commit('CCOLOR')
        }
        

       
    }
}

export default moduleMRPStore;
