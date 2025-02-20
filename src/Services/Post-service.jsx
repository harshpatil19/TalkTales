
import { myAxios } from "./helper"
export const createPost=(postData)=>{
    console.log(postData);
    return myAxios.post(`/api/posts/user/${postData.userid}/category/${postData.categoryId}/post`,postData).then(response=>response.data)

};

export const loadAllPosts=(pageNumber,pageSize)=>{
    return myAxios.get(`/api/posts/post?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=addedDate&sortDir=desc`)
    .then(response => response.data);

};

export const loadPost=(postId)=>{
    return myAxios.get(`/api/posts/${postId}`).then((response)=>response.data)
}

export const createComment = (comment, postId, userid) => {
    if (!postId || !userid) {
        console.error("Error: Invalid postId or userId", { postId, userid });
        return Promise.reject("Invalid postId or userId");
    }

    return myAxios.post(`/api/comments/post/${postId}/user/${userid}`, comment)
        .then(response => response.data)
        .catch(error => {
            console.error("Error in createComment API:", error);
            throw error;
        });
};

