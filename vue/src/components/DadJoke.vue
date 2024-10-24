<template>
    <h2>{{ joke }}</h2>
  </template>
  
  <script>
  import jokeService from '../services/jokeService'; // Adjust the path as necessary
  
  export default {
    data() {
      return {
        joke: ''
      };
    },
    created() {
      jokeService.getJoke()
        .then(response => {
          this.joke = response.data; 
        })
        .catch(error => {
          console.error("Error fetching joke:", error);
        });
    }
  };
  </script>
  