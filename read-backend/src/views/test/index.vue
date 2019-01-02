<template>
    <div :style="{ fontSize: postFontSize + 'em' }">
      <hello 
         v-for="post in posts"
         v-bind:key="post.id"
         v-bind:post="post"
         v-on:enlarge-text="postFontSize += $event"></hello>
      
    </div>    
</template>

<script>
import hello from './components/Hello';

export default {
    components:{
        hello
    },
    data(){
        return{
            posts: [
                    { id: 1, title: 'My journey with Vue' },
                    { id: 2, title: 'Blogging with Vue' },
                    { id: 3, title: 'Why Vue is so fun' }
                ],
            postFontSize: 1
        }
    }
}
</script>
