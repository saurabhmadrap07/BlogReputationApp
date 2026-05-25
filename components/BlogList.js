import React, { useEffect, useState } from 'react';
import { FlatList, Text, TouchableOpacity, View } from 'react-native';

export default function BlogList({ onSelectPost }) {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch('http://<your_backend_ip>:8080/posts')
      .then(res => res.json())
      .then(setPosts)
      .catch(console.error);
  }, []);

  return (
    <FlatList
      data={posts}
      keyExtractor={item => item.id.toString()}
      renderItem={({ item }) => (
        <TouchableOpacity onPress={() => onSelectPost(item)}>
          <View style={{ padding: 12, borderBottomWidth: 1, borderColor: '#222' }}>
            <Text style={{ color: 'white', fontSize: 18 }}>{item.title}</Text>
            <Text style={{ color: 'white' }}>{item.content.slice(0, 60)}...</Text>
          </View>
        </TouchableOpacity>
      )}
    />
  );
}
