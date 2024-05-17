<template>
    <div class="container">
        <div>
            <a class="logo" href="">
                <h1>핏뮤<span class="fitmu">Fitmu</span></h1>
            </a>
        </div>
        <div class="category col col-lg-4">
            <h3>커뮤니티</h3>
            <h3>쇼핑</h3>
        </div>
        <div class="search col col-lg-3">
            <input type="text">
        </div>
        <div class="users">
            <h3>users</h3>
        </div>
    </div>
    <hr>
</template>

<script setup>

</script>

<style scoped>
.container {
    width: 100%;
    margin-top: 10px;
    display: flex;
    align-items: center;
}

.logo {
    text-decoration: none;
    color: black;
}

.fitmu {
    color: #34C5F0;
    font-size: smaller;
    margin-left: 5px;

}

.category {
    display: flex;
    padding-top: 10px;
    justify-content: start;
    padding-left: 50px;
}

h3 {
    font-weight: bold;
}
</style>