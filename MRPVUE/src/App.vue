<template>
  <!-- Don't drop "q-app" class -->
  <q-layout>
    <!-- Header -->
    <div slot="header" class="toolbar">
      <!-- opens left-side drawer using its ref -->
      <button class="hide-on-drawer-visible" @click="$refs.leftDrawer.open()">
                <i>menu</i>
              </button>
      <q-toolbar-title :padding="1">
        MRP - Lauf in der Lehre        
      </q-toolbar-title>

  
    </div>
    <!-- Navigation Tabs -->
    <q-tabs slot="navigation">
      <q-tab icon="home" route="/" exact replace>Startansicht</q-tab>
      <q-tab icon="folder_shared" route="/Algorithm" exact replace>Algorithmusansicht</q-tab>
      <q-tab icon="help" route="/InfoAnsicht" exact replace>Informationen</q-tab>
    </q-tabs>
    <!-- Left-side Drawer 
    <q-drawer ref="leftDrawer">
      <div class="toolbar">
        <q-toolbar-title>
          Hauptmenü
        </q-toolbar-title>
      </div>
      <div class="list no-border platform-delimiter">
        <q-drawer-link icon="home" :to="{path: '/', exact: true}">
          Home
        </q-drawer-link>        
        
        <q-drawer-link icon="find_in_page" :to="{path: '/Algorithm', exact: true}">
          Algorithmusanzeige
        </q-drawer-link>
       
      </div>
    </q-drawer>-->
    <!-- IF USING subRoutes only: -->
    <router-view class="layout-view"></router-view>
    <!-- OR ELSE, IF NOT USING subRoutes: -->
  
    <!-- Right-side Drawer 
            <q-drawer ref="rightDrawer">
              ...
            </q-drawer>
          Footer -->
    <div slot="footer" class="toolbar">
      © By HTW DRESDEN
      
    </div>
  </q-layout>
</template>

<script>
  /*
   * Root component
   */
  export default {}
</script>

<style>
  
</style>
