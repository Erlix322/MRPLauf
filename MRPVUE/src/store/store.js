/*eslint-disable*/
import Vue from 'vue'
import Vuex from 'vuex'
import moduleMRP from './Module_MRP'



Vue.use(Vuex) // Load fancy Vuex

export default new Vuex.Store({

    modules: {
        a: moduleMRP,    
		
    }

})