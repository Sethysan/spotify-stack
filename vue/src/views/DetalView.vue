<template>
    <div>Artist Detail:</div>
    <div v-if="$store.state.artist && $store.state.artist.followers">
        <h2>{{ $store.state.artist.name }}</h2>
        <p>Number of Followers: {{ $store.state.artist.followers.total }}</p>
        <div v-if="$store.state.artist.images.length > 0">
            <img :src="$store.state.artist.images[0].url" :alt="$store.state.artist.name">
        </div>
    </div>
</template>

<script>


</script>