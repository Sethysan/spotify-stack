<template>
  <div class="home">
    <h1>Home</h1>
    <DadJoke />
  </div>
</template>

<script>
import DadJoke from '../components/DadJoke.vue';

export default {
  name: 'Home',
  components: {
    DadJoke
  }
};
</script>
