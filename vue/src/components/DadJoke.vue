<template>
  <h2>{{ joke }}</h2>
  <button @click="saveJoke">Save Joke</button>
</template>

<script>
import jokeService from '../services/JokeService'; // Adjust the path as necessary

export default {
  data() {
    return {
      joke: '', // Holds the fetched joke
      jokeObj: {}, // Holds the joke object if needed in future
    };
  },
  methods: {
    saveJoke() {
      const jokeObj = { joke: this.joke }; // Create a joke object to send
      jokeService.saveJoke(jokeObj) // Pass the object to saveJoke service
        .then((response) => {
          if(response.status ===200 ){
          alert("joke saved to database!")
          }
          
        })
        .catch((error) => {
          console.error("Error saving joke:", error);
        });
    }
  },
  created() {
    jokeService.getJoke() // Fetch a random joke on component creation
      .then(response => {
        this.joke = response.data; 
      })
      .catch(error => {
        console.error("Error fetching joke:", error);
      });
  }
};
</script>
