<template>
  <div>Article {{ $route.params.searchKey }}</div>
</template>
<script>
export default {
  created() {
    console.log(this.$route.params.searchKey);
  },
};
</script>