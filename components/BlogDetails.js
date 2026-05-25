import React, { useEffect, useState } from 'react';
import { Text, FlatList, TextInput, Button, View } from 'react-native';

export default function BlogDetails({ post, onBack }) {
  const [comments, setComments] = useState([]);
  const [commentText, setCommentText] = useState('');

  useEffect(() => {
    fetch(`http://<your_backend_ip>:8080/posts/${post.id}/comments`)
      .then(res => res.json())
      .then(setComments);
  }, [post]);

  const addComment = () => {
    if (!commentText.trim()) return;
    fetch(`http://<your_backend_ip>:8080/comments/${post.id}/add?userId=1`, {
      method: 'POST',
      headers: { 'Content-Type': 'text/plain' },
      body: commentText,
    }).then(() => {
      setCommentText('');
      fetch(`http://<your_backend_ip>:8080/posts/${post.id}/comments`)
        .then(res => res.json())
        .then(setComments);
    });
  };

  const vote = async (targetType, targetId, isUpvote) => {
    try {
      await fetch(
        `http://<your_backend_ip>:8080/votes/cast?targetType=${targetType}&targetId=${targetId}&userId=1&isUpvote=${isUpvote}`,
        {
          method: 'POST',
        }
      );
      // Optionally refresh comments or post details after voting
    } catch (err) {
      console.error(err);
    }
  };

  // Usage within BlogDetails UI (add under post content)
  <View style={{ flexDirection: 'row', marginBottom: 10 }}>
    <Button title="Upvote" onPress={() => vote('post', post.id, true)} />
    <View style={{ width: 16 }} />
    <Button title="Downvote" onPress={() => vote('post', post.id, false)} />
  </View>;


  return (
    <View style={{ flex: 1, backgroundColor: 'black', padding: 16 }}>
      <Button title="Back" onPress={onBack} />
      <Text style={{ fontSize: 22, fontWeight: 'bold', color: 'white' }}>{post.title}</Text>
      <Text style={{ color: 'white', marginBottom: 12 }}>{post.content}</Text>
      <Text style={{ color: 'white', fontWeight: 'bold' }}>Comments:</Text>
      <FlatList
        data={comments}
        keyExtractor={item => item.id.toString()}
        renderItem={({ item }) => (
          <Text style={{ color: 'white', paddingVertical: 6 }}>{item.content}</Text>
        )}
      />
      <TextInput
        style={{
          backgroundColor: 'white',
          color: 'black',
          padding: 10,
          marginVertical: 10,
          borderRadius: 6,
        }}
        value={commentText}
        onChangeText={setCommentText}
        placeholder="Add comment"
      />
      <Button title="Post Comment" onPress={addComment} />
      {/* Add Upvote/Downvote buttons next if needed */}
    </View>
  );
}
