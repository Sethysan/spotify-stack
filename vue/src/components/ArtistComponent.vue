<template>
    <label for="artistName">Name of Artist: </label>
    <input type="text" v-model="artistName">
    <button @click="getArtist">Get Information!</button>
    <div v-if="artist && artist.followers">
        <h2>{{ artistName }}</h2>
        <p>Number of Followers: {{ artist.followers.total }}</p>
        <div v-if="artist.images.length >0">
            <img v-for="(index, index) in artist.images" :key="index" src="image.url" alt="artist.name">
        </div>
    </div>
    <div v-else-if="error">
        <p> Error fetching artist data: {{ error }}</p>
    </div>
</template>

<script>
import artistService from '../services/ArtistService';

export default {
    data() {
        return {
            artistName: '',
            artist: {},
            error: null
        };
    },
    methods: {
        getArtist() {
            artistService.getArtist(this.artistName)
                .then((response) => {
                    console.log('response.data: ' ,  response.data)
                    this.artist = response.data;
                })
                .catch((error) => {
                    this.error = error.response ? error.response.data.message : error.message;
                    console.error("Error fetching artist data:", error);
                });
        }
    }
};
</script>
