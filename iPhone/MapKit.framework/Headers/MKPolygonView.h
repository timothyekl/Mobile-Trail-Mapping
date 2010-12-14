//
//  MKPolygonView.h
//  MapKit
//
//  Copyright 2010 Apple, Inc. All rights reserved.
//

#if __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED

#import <UIKit/UIKit.h>

#import <MapKit/MKPolygon.h>
#import <MapKit/MKOverlayPathView.h>

@interface MKPolygonView : MKOverlayPathView

- (id)initWithPolygon:(MKPolygon *)polygon;
@property (nonatomic, readonly) MKPolygon *polygon;

@end

#endif
