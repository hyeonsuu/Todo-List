<template>
  <div id="app">
    <div class="top">
      <ToDoHeader />
      <div v-if="this.storedName">
        <ToDoTitle />
        <ToDoInput v-on:alertModal="showModal" @reload="reload"/>
      </div>
      <div v-else>
        <ToDoHello />
      </div>
    </div>
    <div class="body">
      <div v-if="this.storedName">
        <ToDoController />
        <ToDoList ref="list" />
      </div>
      <ToDoFooter />
    </div>
    <ToDoModal v-show="modalVisible" v-on:close="modalVisible = false">
      <template v-slot:modal-text>{{ modalText }}</template>
    </ToDoModal>
  </div>
</template>

<script>
import ToDoHeader from "./components/ToDoHeader";
import ToDoTitle from "./components/ToDoTitle";
import ToDoInput from "./components/ToDoInput";
import ToDoController from "./components/ToDoController";
import ToDoList from "./components/ToDoList";
import ToDoFooter from "./components/ToDoFooter";
import ToDoHello from "./components/ToDoHello";
import ToDoModal from "./components/common/ToDoModal";
import { mapGetters } from "vuex";
export default {
  name: "App",
  data() {
    return {
      modalVisible: false,
      modalText: ""
    };
  },
  computed: {
    ...mapGetters(["storedName"])
  },
  methods: {
    showModal(text) {
      this.modalText = text;
      this.modalVisible = !this.modalVisible;
    },
    reload(){
      this.$refs.list.getBoardList();
    }
  },
  components: {
    ToDoHeader,
    ToDoTitle,
    ToDoInput,
    ToDoController,
    ToDoList,
    ToDoFooter,
    ToDoHello,
    ToDoModal
  }
};
</script>

<style lang="scss">
@import "./assets/style/_reset";
.top {
  width: 100%;
  min-height: 43.6rem;
  padding: 0 $padding 4.5rem;
  background-image: linear-gradient(145deg, #3770cc 20%, #ce91c9 84%);
}
.body {
  padding: 3rem $padding;
  background-color: #efefef;
}
</style>