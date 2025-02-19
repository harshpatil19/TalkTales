
import { myAxios } from "./helper"
export const createPost=(postData)=>{
    console.log(postData);
    return myAxios.post(`/api/posts/user/${postData.userid}/category/${postData.categoryId}/post`,postData).then(response=>response.data)

};

export const loadAllPosts=(pageNumber,pageSize)=>{
    return myAxios.get(`/api/posts/post?pageNumber=${pageNumber}$pageSize=${pageSize}&sortBy=addedDate&sortDir=desc`).then((response) => response.data)
};

export const loadPost=(postId)=>{
    return myAxios.get(`/api/posts/${postId}`).then((response)=>response.data)
}