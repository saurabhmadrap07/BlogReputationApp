import React, { useState } from 'react';
import { View } from 'react-native';
import BlogList from './components/BlogList';
import BlogDetails from './components/BlogDetails';

export default function App() {
  const [selectedPost, setSelectedPost] = useState(null);

  return (
    <View style={{ flex: 1, backgroundColor: 'black' }}>
      {selectedPost ? (
        <BlogDetails post={selectedPost} onBack={() => setSelectedPost(null)} />
      ) : (
        <BlogList onSelectPost={setSelectedPost} />
      )}
    </View>
  );
}
