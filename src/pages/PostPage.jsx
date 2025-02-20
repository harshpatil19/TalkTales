import { Link, useParams } from "react-router-dom";
import Base from "../components/Base";
import {
  Button,
  Card,
  CardBody,
  CardText,
  Col,
  Container,
  Input,
  Row,
} from "reactstrap";
import { useEffect, useState } from "react";
import { createComment, loadPost } from "../Services/post-service";
import { toast } from "react-toastify";
import { BASE_URL } from "../Services/helper";

const PostPage = () => {
  const { postId } = useParams();
  const [post, setPost] = useState(null);
  const [comment, setComment] = useState({ content: "" });

  useEffect(() => {
    console.log("Post ID from URL params:", postId);
    if (!postId) return;

    loadPost(postId)
      .then((data) => {
        console.log("API Response:", data);
        setPost({ ...data, comments: data.comments || [] });
      })
      .catch((error) => {
        console.error("Error fetching post:", error);
        toast.error("Error in loading post");
      });
  }, [postId]);

  const printDate = (numbers) => {
    return new Date(numbers).toLocaleDateString();
  };

  // ✅ Submit comment function (Fixed)
  const submitPost = () => {
    if (!post || !post.user || !post.user.userid) {
      toast.error("Invalid post or user data");
      console.error("Error: Post or User ID is undefined", { post, user: post?.user });
      return;
    }

    console.log("Submitting comment for Post ID:", post.postId, "User ID:", post.user.userid);

    createComment({ content: comment.content }, post.postId, post.user.userid)
      .then((data) => {
        console.log("Comment added:", data);
        toast.success("Comment added successfully!");
        setComment({ content: '' });

        // ✅ Update the UI to reflect the new comment
        setPost((prevPost) => ({
          ...prevPost,
          comments: [...prevPost.comments, data], // Append new comment to the existing comments
        }));
      })
      .catch((error) => {
        console.error("Error adding comment:", error);
        toast.error("Failed to add comment.");
      });
  };

  return (
    <Base>
      <Container className="mt-4">
        <Link to="/">Home</Link> / {post && <Link to="">{post.title}</Link>}
        <Row>
          <Col md={{ size: 12 }}>
            <Card className="mt-3 ps-2 border-0 shadow">
              {post && (
                <CardBody>
                  <CardText>
                    Posted By <b>{post?.user ? post.user.name : "Unknown"}</b>{" "}
                    on <b>{printDate(post.addedDate)}</b>
                  </CardText>
                  <CardText>
                    <span className="text-muted">{post.category.categoryTitle}</span>
                  </CardText>
                  <div className="divider" style={{ width: "100%", height: "1px", background: "#e2e2e2" }}></div>

                  <CardText className="mt-3">{post.title}</CardText>
                  <div className="mt-3">
                    <h2>{post.title}</h2>
                  </div>

                  <div className="image-container mt-4 container text-center shadow" style={{ width: "50%" }}>
                    <img className="img-fluid" src={BASE_URL + "/api/posts/image/" + post.imageName} alt="" />
                  </div>

                  <div className="mt-5" dangerouslySetInnerHTML={{ __html: post.content }}></div>
                </CardBody>
              )}
            </Card>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col md={{ size: 9, offset: 1 }}>
            <h3>Comments ({post ? post.comments.length : 0})</h3>
            {post && post.comments.length > 0 ? (
              post.comments.map((c, index) => (
                <Card className="mt-2 border-0" key={index}>
                  <CardBody>
                    <CardText>{c.content}</CardText>
                  </CardBody>
                </Card>
              ))
            ) : (
              <p>No comments yet.</p>
            )}

            <Card className="mt-2 border-0">
              <CardBody>
                <Input
                  type="textarea"
                  placeholder="Enter comment here"
                  value={comment.content} // ✅ Fix: Corrected key from `comment.comment` to `comment.content`
                  onChange={(event) =>
                    setComment({ content: event.target.value })
                  }
                />
                <Button onClick={submitPost} className="mt-2" color="primary">
                  Submit
                </Button>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default PostPage;
